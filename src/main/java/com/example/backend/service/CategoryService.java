package com.example.backend.service;

import com.example.backend.dto.CategoryCreationRequest;
import com.example.backend.entity.Category;
import com.example.backend.reponsitory.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> getCategory() {
        return categoryRepository.findAll();
    }

    public Category createCategory(CategoryCreationRequest request) {
        Category category = new Category();
        category.setName(request.getName());
        category.setImageUrl(request.getImageUrl());
        return categoryRepository.save(category);
    }

    public Optional<Category> getCategoryById(long id) {
        return categoryRepository.findById(id);
    }
}
