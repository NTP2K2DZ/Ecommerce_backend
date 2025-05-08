package com.example.backend.mapper;

import com.example.backend.dto.*;
import com.example.backend.entity.*;

import java.util.List;
import java.util.stream.Collectors;

public class ProductMapper {

    // Entity -> DTO
    public static ProductCreationRequest toDTO(Product product) {
        return new ProductCreationRequest(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getQuantity(),
                product.getCreatedAt(),
                toCategoryDTO(product.getCategory()),
                toImageDTOList(product.getImages())
        );
    }

    // DTO -> Entity
    public static Product toEntity(ProductCreationRequest dto, Category category) {
        Product product = new Product();
        product.setName(dto.getName());
        product.setDescription(dto.getDescription());
        product.setPrice(dto.getPrice());
        product.setQuantity(dto.getQuantity());
        product.setCategory(category);
        return product;
    }

    private static CategoryCreationRequest toCategoryDTO(Category category) {
        if (category == null) return null;
        return new CategoryCreationRequest(category.getId(), category.getName(), category.getImageUrl());
    }

    private static List<ProductImageCreationRequest> toImageDTOList(List<ProductImage> images) {
        if (images == null) return null;
        return images.stream()
                .map(img -> new ProductImageCreationRequest(img.getId(), img.getImageUrl()))
                .collect(Collectors.toList());
    }
}
