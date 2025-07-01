package com.example.backend.controller;

import com.example.backend.dto.request.product.ProductOptionValueRequest;
import com.example.backend.dto.response.product.ProductOptionValueResponse;
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
    public ResponseEntity<List<ProductOptionValueResponse>> getAllOptionValue() {
        return ResponseEntity.ok(productOptionValueService.getAllOptionValues());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductOptionValueResponse> getValueById(Long id) {
        return  ResponseEntity.ok(productOptionValueService.getValueById(id));
    }

    @GetMapping("/by-option/{optionId}")
    public ResponseEntity<List<ProductOptionValueResponse>> getValuesByOptionId(@PathVariable Long optionId) {
        return ResponseEntity.ok(productOptionValueService.getOptionValuesByOptionId(optionId));
    }

    @PostMapping
    public ResponseEntity<ProductOptionValueResponse> createOptionValue(
            @RequestBody ProductOptionValueRequest request) {

        ProductOptionValueResponse created = productOptionValueService.createOptionValue(request);
        return ResponseEntity.ok(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductOptionValueResponse> updateOptionValue(
            @PathVariable Long id,
            @RequestBody ProductOptionValueRequest request) {
        return ResponseEntity.ok(productOptionValueService.updateOptionValue(id, request));
    }


    @DeleteMapping("/{id}")
    public  ResponseEntity<Void> deleteOptionValue(@PathVariable Long id) {
        productOptionValueService.deleteOptionValue(id);
        return ResponseEntity.noContent().build();
    }

}
