package com.example.backend.controller;

import com.example.backend.dto.CategoryCreationRequest;
import com.example.backend.entity.Category;
import com.example.backend.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    Category createCategory(@RequestBody CategoryCreationRequest request) {
        return categoryService.createCategory(request);
    }

    @GetMapping
    List<Category> getCategory() {
        return categoryService.getCategory();
    }

    @GetMapping("/{categoryId}")
    Optional<Category> getCategoryById(@PathVariable("categoryId") long categoryId) {
        return categoryService.getCategoryById(categoryId);
    }
}
