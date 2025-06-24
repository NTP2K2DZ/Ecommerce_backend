package com.example.backend.service;

import com.example.backend.dto.category.CategoryCreationRequest;
import com.example.backend.dto.category.CategoryUpdateRequest;
import com.example.backend.entity.Category;
import com.example.backend.entity.Product;
import com.example.backend.exception.BadRequestException;
import com.example.backend.exception.ResourceNotFoundException;
import com.example.backend.reponsitory.CategoryRepository;
import com.example.backend.reponsitory.ProductRepository;
import com.example.backend.util.SlugUtil;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;

    public CategoryService(CategoryRepository categoryRepository, ProductRepository productRepository) {
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
    }

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with id: " + id));
    }

    @Transactional
    public Category createCategory(CategoryCreationRequest request) {

        String name = request.getName();
        String slug = SlugUtil.toCategorySlug(request.getName());

        if (categoryRepository.existsByName(name)) {
            throw new BadRequestException("Brand name already exists: " + name);
        }

        if (categoryRepository.existsBySlug(slug)) {
            throw new BadRequestException("Brand slug already exists: " + slug);
        }

        Category category = new Category();
        category.setName(request.getName());
        category.setImageUrl(request.getImageUrl());
        category.setSlug(slug);
        System.out.println("Generated slug: " + slug);
        return categoryRepository.save(category);
    }

    @Transactional
    public Category updateCategory(Long id, CategoryUpdateRequest request) {
        Category existingCategory = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with id: " + id));

        String newName = request.getName();
        String newSlug = SlugUtil.toCategorySlug(newName);

        if (!newName.equalsIgnoreCase(existingCategory.getName()) &&
                categoryRepository.existsByNameAndIdNot(newName, id)) {
            throw new BadRequestException("Category name already exists: " + newName);
        }

        if (!newSlug.equalsIgnoreCase(existingCategory.getSlug()) &&
                categoryRepository.existsBySlugAndIdNot(newSlug, id)) {
            throw new BadRequestException("Category slug already exists: " + newSlug);
        }

        existingCategory.setName(newName);
        existingCategory.setSlug(newSlug);
        existingCategory.setImageUrl(request.getImageUrl());

        return categoryRepository.save(existingCategory);
    }

    @Transactional
    public void deleteCategory(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with id: " + id));

        boolean hasProducts = productRepository.existsByCategoryId(id);
        if (hasProducts) {
            throw new BadRequestException("Cannot delete category because it is associated with existing products");
        }
        categoryRepository.delete(category);
    }

}
