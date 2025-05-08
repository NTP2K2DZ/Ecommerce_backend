package com.example.backend.controller;

import com.example.backend.dto.ProductCreationRequest;
import com.example.backend.entity.Product;
import com.example.backend.reponsitory.ProductImageRepository;
import com.example.backend.reponsitory.ProductRepository;
import com.example.backend.service.ProductImageService;
import com.example.backend.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    private final ProductService productService;
    private final ProductImageService productImageService;

    public ProductController(ProductService productService, ProductImageService productImageService) {
        this.productService = productService;
        this.productImageService = productImageService;
    }

    @GetMapping
    public ResponseEntity<List<ProductCreationRequest>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductCreationRequest> getProductById(@PathVariable Long id) {
        return ResponseEntity.ok(productService.getProductById(id));
    }

    @PostMapping
    public ResponseEntity<ProductCreationRequest> createProduct(@RequestBody ProductCreationRequest dto) {
        return ResponseEntity.ok(productService.createProduct(dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productImageService.deleteAllImagesByProductId(id);
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}

