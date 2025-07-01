package com.example.backend.service;

import com.example.backend.dto.request.product.ProductRequest;
import com.example.backend.dto.response.product.ProductResponse;

import java.util.List;

public interface ProductService {
    List<ProductResponse> getAllProducts();
    ProductResponse getProductById(Long id);
    List<ProductResponse> getProductsByCategoryId(Long categoryId);
    List<ProductResponse> getProductsByBrandId(Long brandId);
    ProductResponse createProduct(ProductRequest request);
    ProductResponse updateProduct(Long id, ProductRequest request);
    void deleteProduct(Long id);
}
