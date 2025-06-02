package com.example.backend.controller;

import com.example.backend.dto.category.CategoryCreationRequest;
import com.example.backend.dto.category.CategoryUpdateRequest;
import com.example.backend.entity.Category;
import com.example.backend.service.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/category")
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController (CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public ResponseEntity<List<Category>> getAllCategories() {
        return ResponseEntity.ok(categoryService.getAllCategories());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable Long id) {
        Category category = categoryService.getCategoryById(id);
        return ResponseEntity.ok(category);
    }

    @PostMapping
    public ResponseEntity<Category> createCategory(@RequestBody CategoryCreationRequest request) {
        return ResponseEntity.ok(categoryService.createCategory(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Category> updateCategory(@PathVariable Long id, @RequestBody CategoryUpdateRequest request) {
        return ResponseEntity.ok(categoryService.updateCategory(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }

}
