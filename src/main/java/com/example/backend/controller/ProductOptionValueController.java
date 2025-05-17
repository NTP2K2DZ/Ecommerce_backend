package com.example.backend.controller;

import com.example.backend.dto.ProductOptionValueCreationRequest;
import com.example.backend.service.ProductOptionValueService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/product_values")
public class ProductOptionValueController {

    // ================= Dependencies =================
    public final ProductOptionValueService productOptionValueService;

    // ================= Constructor =================
    public ProductOptionValueController(ProductOptionValueService productOptionValueService) {
        this.productOptionValueService =productOptionValueService;
    }

    // ================= GET Methods =================
    @GetMapping
    public ResponseEntity<List<ProductOptionValueCreationRequest>> getAllOptionValue() {
        return ResponseEntity.ok(productOptionValueService.getAllOptionValues());
    }

    @GetMapping("/{optionId}")
    public ResponseEntity<List<ProductOptionValueCreationRequest>> getOptionValuesByOptionId(@PathVariable Long optionId) {
        return ResponseEntity.ok(productOptionValueService.getOptionValuesByOptionId(optionId));
    }

    // ================= POST Methods =================
    @PostMapping
    public ResponseEntity<ProductOptionValueCreationRequest> createOptionValue(@RequestBody ProductOptionValueCreationRequest request) {
        ProductOptionValueCreationRequest created = productOptionValueService.createOptionValue(request);
        return  ResponseEntity.ok(created);
    }

    // ================= PUT Methods =================
    @PutMapping("/{id}")
    public ResponseEntity<ProductOptionValueCreationRequest> updateOptionValue(@PathVariable Long id, @RequestBody ProductOptionValueCreationRequest request) {
        ProductOptionValueCreationRequest updated = productOptionValueService.updateOptionValue(id, request);
        return ResponseEntity.ok(updated);
    }

    // ================= DELETE Methods =================
    @DeleteMapping("{id}")
    public  ResponseEntity<Void> deleteOptionValue(@PathVariable Long id) {
        productOptionValueService.deleteOptionValue(id);
        return ResponseEntity.noContent().build();
    }

}
