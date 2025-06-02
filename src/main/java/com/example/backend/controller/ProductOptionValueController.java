package com.example.backend.controller;

import com.example.backend.dto.product_option_value.ProductOptionValueCreationRequest;
import com.example.backend.service.ProductOptionValueService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/product_values")
public class ProductOptionValueController {

    public final ProductOptionValueService productOptionValueService;

    public ProductOptionValueController(ProductOptionValueService productOptionValueService) {
        this.productOptionValueService =productOptionValueService;
    }

    @GetMapping
    public ResponseEntity<List<ProductOptionValueCreationRequest>> getAllOptionValue() {
        return ResponseEntity.ok(productOptionValueService.getAllOptionValues());
    }

    @GetMapping("/{optionId}")
    public ResponseEntity<List<ProductOptionValueCreationRequest>> getOptionValuesByOptionId(@PathVariable Long optionId) {
        return ResponseEntity.ok(productOptionValueService.getOptionValuesByOptionId(optionId));
    }

    @PostMapping
    public ResponseEntity<ProductOptionValueCreationRequest> createOptionValue(@RequestBody ProductOptionValueCreationRequest request) {
        ProductOptionValueCreationRequest created = productOptionValueService.createOptionValue(request);
        return  ResponseEntity.ok(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductOptionValueCreationRequest> updateOptionValue(@PathVariable Long id, @RequestBody ProductOptionValueCreationRequest request) {
        ProductOptionValueCreationRequest updated = productOptionValueService.updateOptionValue(id, request);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("{id}")
    public  ResponseEntity<Void> deleteOptionValue(@PathVariable Long id) {
        productOptionValueService.deleteOptionValue(id);
        return ResponseEntity.noContent().build();
    }

}
