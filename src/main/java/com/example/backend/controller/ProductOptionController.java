package com.example.backend.controller;

import com.example.backend.dto.ProductCreationRequest;
import com.example.backend.dto.ProductOptionCreationRequest;
import com.example.backend.entity.ProductOption;
import com.example.backend.service.ProductOptionService;
import com.example.backend.service.ProductService;
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
    public ResponseEntity<List<ProductOptionCreationRequest>> getAllProductOption() {
        return ResponseEntity.ok(productOptionService.getAllOptions());
    }

    @PostMapping
    public ResponseEntity<ProductOptionCreationRequest> createOption(@RequestBody ProductOptionCreationRequest dto) {
        return ResponseEntity.ok(productOptionService.createOption(dto));
    }

    @GetMapping("product/{productId}")
    public ResponseEntity<List<ProductOptionCreationRequest>> getOptionByProductId(@PathVariable Long productId) {
        return ResponseEntity.ok(productOptionService.getOptionsByProductId(productId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOption(@PathVariable Long id) {
        productOptionService.deleteOption(id);
        return ResponseEntity.noContent().build();
    }
}
