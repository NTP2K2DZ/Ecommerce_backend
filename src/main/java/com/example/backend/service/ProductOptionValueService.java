package com.example.backend.service;

import com.example.backend.dto.request.product.ProductOptionValueRequest;
import com.example.backend.dto.response.product.ProductOptionValueResponse;

import java.util.List;

public interface ProductOptionValueService {
    List<ProductOptionValueResponse> getAllOptionValues();

    ProductOptionValueResponse getValueById(Long id);

    List<ProductOptionValueResponse> getOptionValuesByOptionId(Long optionId);

    ProductOptionValueResponse createOptionValue(ProductOptionValueRequest request);

    void deleteOptionValue(Long id);

    ProductOptionValueResponse updateOptionValue(Long id, ProductOptionValueRequest request);
}
