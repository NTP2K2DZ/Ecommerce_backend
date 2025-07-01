package com.example.backend.controller;

import com.example.backend.dto.request.product.ProductVariantRequest;
import com.example.backend.dto.response.product.ProductVariantResponse;
import com.example.backend.service.ProductVariantService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/product_variants")
public class ProductVariantController {

    private final ProductVariantService productVariantService;

    public ProductVariantController(ProductVariantService productVariantService) {
        this.productVariantService = productVariantService;
    }

    @GetMapping
    public ResponseEntity<List<ProductVariantResponse>> getAllVariants() {
        List<ProductVariantResponse> response = productVariantService.getAllVariants();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/by-product/{productId}")
    public ResponseEntity<List<ProductVariantResponse>> getVariantsByProductId(@PathVariable Long productId) {
        List<ProductVariantResponse> response = productVariantService.getVariantsByProductId(productId);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<ProductVariantResponse> createVariant(@RequestBody ProductVariantRequest request) {
        ProductVariantResponse response = productVariantService.createVariant(request);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{variantId}")
    public ResponseEntity<ProductVariantResponse> updateVariant(@PathVariable Long variantId, @RequestBody ProductVariantRequest request) {
        ProductVariantResponse response = productVariantService.updateVariant(variantId, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{variantId}")
    public ResponseEntity<Void> deleteVariant(@PathVariable Long variantId) {
        productVariantService.deleteVariant(variantId);
        return ResponseEntity.noContent().build();
    }
}

