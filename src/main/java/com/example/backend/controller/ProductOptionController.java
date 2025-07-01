package com.example.backend.controller;

import com.example.backend.dto.request.product.ProductOptionRequest;
import com.example.backend.dto.response.product.ProductOptionResponse;
import com.example.backend.service.ProductOptionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/product_options")
public class ProductOptionController {
    private final ProductOptionService productOptionService;

    public ProductOptionController(ProductOptionService productOptionService) {
        this.productOptionService = productOptionService;
    }
    @GetMapping
    public ResponseEntity<List<ProductOptionResponse>> getAllProductOption() {
        return ResponseEntity.ok(productOptionService.getAllOptions());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductOptionResponse> getOptionById(@PathVariable Long id) {
        return ResponseEntity.ok(productOptionService.getOptionById(id));
    }

    @PostMapping
    public ResponseEntity<ProductOptionResponse> createOption(@RequestBody ProductOptionRequest request) {
        return ResponseEntity.ok(productOptionService.createOption(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductOptionResponse> updateOption(@PathVariable Long id, @RequestBody ProductOptionRequest request) {
        return ResponseEntity.ok(productOptionService.updateOption(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOption(@PathVariable Long id) {
        productOptionService.deleteOption(id);
        return ResponseEntity.noContent().build();
    }
}
