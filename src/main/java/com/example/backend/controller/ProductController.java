package com.example.backend.controller;

import com.example.backend.dto.product.ProductCreationRequest;
import com.example.backend.dto.product.ProductRequest;
import com.example.backend.dto.product.ProductUpdateRequest;
import com.example.backend.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<List<ProductRequest>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductRequest> getProductById(@PathVariable Long id) {
        return ResponseEntity.ok(productService.getProductById(id));
    }

    @GetMapping("/category/{categoryId}")
    public List<ProductRequest> getProductsByCategoryId(@PathVariable Long categoryId) {
        return productService.getProductsByCategoryId(categoryId);
    }

    @PostMapping
    public ResponseEntity<ProductRequest> createProduct(@RequestBody ProductCreationRequest dto) {
        return ResponseEntity.ok(productService.createProduct(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductRequest> updateProduct(@PathVariable Long id, @RequestBody ProductUpdateRequest dto) {
        return ResponseEntity.ok(productService.updateProduct(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}

