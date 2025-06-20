package com.example.backend.mapper;

import com.example.backend.dto.category.CategoryResponse;
import com.example.backend.dto.product.ProductCreationRequest;
import com.example.backend.dto.product.ProductResponse;
import com.example.backend.dto.product.ProductUpdateRequest;
import com.example.backend.entity.*;
import java.util.List;
import java.util.stream.Collectors;

public class ProductMapper {
    // Entity -> DTO Request
    // product return data
    public static ProductResponse toResponseDTO(Product product) {
        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getQuantity(),
                product.getCategory().getName(),
                toImageUrlList(product.getImages())
        );
    }

    // DTO -> Entity Create
    public static Product toEntityCreate(ProductCreationRequest request, Category category) {
        Product product = new Product();
        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());
        product.setQuantity(request.getQuantity());
        product.setCategory(category);
        return product;
    }

    // DTO -> Entity Update
    public static Product toEntityUpdate(ProductUpdateRequest request, Category category) {
        Product product = new Product();
        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());
        product.setQuantity(request.getQuantity());
        product.setCategory(category);
        return product;
    }

    private static CategoryResponse toCategoryDTO(Category category) {
        if (category == null) return null;
        return new CategoryResponse(category.getId(), category.getName(), category.getImageUrl());
    }

    public static List<String> toImageUrlList(List<ProductImage> images) {
        if (images == null) {
            return List.of();
        }
        return images.stream()
                .map(ProductImage::getImageUrl)
                .toList();
    }

    public static List<ProductResponse> toResponseList(List<Product> products) {
        return products.stream().map(ProductMapper::toResponseDTO).collect(Collectors.toList());
    }

}
