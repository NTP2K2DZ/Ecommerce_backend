package com.example.backend.service;

import com.example.backend.dto.request.product.ProductVariantRequest;
import com.example.backend.dto.response.product.ProductVariantResponse;

import java.util.List;

public interface ProductVariantService {
    List<ProductVariantResponse> getAllVariants();
    List<ProductVariantResponse> getVariantsByProductId(Long productId);
    ProductVariantResponse createVariant(ProductVariantRequest request);
    ProductVariantResponse updateVariant(Long variantId, ProductVariantRequest request);
    void deleteVariant(Long variantId);
}
