package com.example.backend.controller;

import com.example.backend.dto.product.ProductCreationRequest;
import com.example.backend.dto.product.ProductResponse;
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

//    @GetMapping
//    public ResponseEntity<List<ProductResponse>> getAllProducts(
//            @RequestParam(required = false) Long categoryId,
//            @RequestParam(required = false) Long brandId) {
//
//        if (categoryId != null && brandId != null) {
//            return ResponseEntity.ok(productService.getProductsByCategoryAndBrand(categoryId, brandId));
//        }
//
//        if (categoryId != null) {
//            return ResponseEntity.ok(productService.getProductsByCategoryId(categoryId));
//        }
//
//        if (brandId != null) {
//            return ResponseEntity.ok(productService.getProductsByBrandId(brandId));
//        }
//
//        return ResponseEntity.ok(productService.getAllProducts());
//    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getProductById(@PathVariable Long id) {
        return ResponseEntity.ok(productService.getProductById(id));
    }

    @PostMapping
    public ResponseEntity<ProductResponse> createProduct(@RequestBody ProductCreationRequest request) {
        return ResponseEntity.ok(productService.createProduct(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponse> updateProduct(@PathVariable Long id, @RequestBody ProductUpdateRequest request) {
        return ResponseEntity.ok(productService.updateProduct(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}

