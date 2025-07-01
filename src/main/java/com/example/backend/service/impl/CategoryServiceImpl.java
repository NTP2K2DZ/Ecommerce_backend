package com.example.backend.service.impl;

import com.example.backend.dto.request.CategoryRequest;
import com.example.backend.entity.Category;
import com.example.backend.exception.BadRequestException;
import com.example.backend.exception.ResourceNotFoundException;
import com.example.backend.reponsitory.CategoryRepository;
import com.example.backend.reponsitory.ProductRepository;
import com.example.backend.service.CategoryService;
import com.example.backend.util.SlugUtil;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository, ProductRepository productRepository) {
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with id: " + id));
    }

    @Override
    @Transactional
    public Category createCategory(CategoryRequest request) {
        String name = request.getName();
        String slug = SlugUtil.toCategorySlug(name);

        if (categoryRepository.existsByName(name)) {
            throw new BadRequestException("Category name already exists: " + name);
        }

        if (categoryRepository.existsBySlug(slug)) {
            throw new BadRequestException("Category slug already exists: " + slug);
        }

        Category category = new Category();
        category.setName(name);
        category.setImageUrl(request.getImageUrl());
        category.setSlug(slug);

        return categoryRepository.save(category);
    }

    @Override
    @Transactional
    public Category updateCategory(Long id, CategoryRequest request) {
        Category existingCategory = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with id: " + id));

        String newName = request.getName();
        String newSlug = SlugUtil.toCategorySlug(newName);

        if (!newName.equalsIgnoreCase(existingCategory.getName())
                && categoryRepository.existsByNameAndIdNot(newName, id)) {
            throw new BadRequestException("Category name already exists: " + newName);
        }

        if (!newSlug.equalsIgnoreCase(existingCategory.getSlug())
                && categoryRepository.existsBySlugAndIdNot(newSlug, id)) {
            throw new BadRequestException("Category slug already exists: " + newSlug);
        }

        existingCategory.setName(newName);
        existingCategory.setSlug(newSlug);
        existingCategory.setImageUrl(request.getImageUrl());

        return categoryRepository.save(existingCategory);
    }

    @Override
    @Transactional
    public void deleteCategory(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with id: " + id));

        if (productRepository.existsByCategoryId(id)) {
            throw new BadRequestException("Cannot delete category because it is associated with existing products");
        }

        categoryRepository.delete(category);
    }
}
