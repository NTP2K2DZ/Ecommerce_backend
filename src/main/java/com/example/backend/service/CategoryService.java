package com.example.backend.service;

import com.example.backend.dto.request.CategoryRequest;
import com.example.backend.entity.Category;

import java.util.List;

public interface CategoryService {
    List<Category> getAllCategories();

    Category getCategoryById(Long id);

    Category createCategory(CategoryRequest request);

    Category updateCategory(Long id, CategoryRequest request);

    void deleteCategory(Long id);
}
