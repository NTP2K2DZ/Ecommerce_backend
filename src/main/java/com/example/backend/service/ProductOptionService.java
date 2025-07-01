package com.example.backend.service;

import com.example.backend.dto.request.product.ProductOptionRequest;
import com.example.backend.dto.response.product.ProductOptionResponse;

import java.util.List;

public interface ProductOptionService {
    List<ProductOptionResponse> getAllOptions();
    ProductOptionResponse getOptionById(Long id);
    ProductOptionResponse createOption(ProductOptionRequest request);
    ProductOptionResponse updateOption(Long id, ProductOptionRequest request);
    void deleteOption(Long id);
}
