package com.example.backend.mapper;

import com.example.backend.dto.category.CategoryRequest;
import com.example.backend.dto.product.ProductCreationRequest;
import com.example.backend.dto.product.ProductRequest;
import com.example.backend.dto.product.ProductUpdateRequest;
import com.example.backend.dto.product_image.ProductImageCreationRequest;
import com.example.backend.dto.product_image.ProductImageRequest;
import com.example.backend.entity.*;
import java.util.List;
import java.util.stream.Collectors;

public class ProductMapper {
    // Entity -> DTO Request
    // product return data
    public static ProductRequest toDTORequest(Product product) {
        return new ProductRequest(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getQuantity(),
                toCategoryDTO(product.getCategory()),
                toImageUrlList(product.getImages())
        );
    }

    // DTO -> Entity Create
    public static Product toEntityCreate(ProductCreationRequest dto, Category category) {
        Product product = new Product();
        product.setName(dto.getName());
        product.setDescription(dto.getDescription());
        product.setPrice(dto.getPrice());
        product.setQuantity(dto.getQuantity());
        product.setCategory(category);
        return product;
    }

    // DTO -> Entity Update
    public static Product toEntityUpdate(ProductUpdateRequest dto, Category category) {
        Product product = new Product();
        product.setName(dto.getName());
        product.setDescription(dto.getDescription());
        product.setPrice(dto.getPrice());
        product.setQuantity(dto.getQuantity());
        product.setCategory(category);
        return product;
    }

    private static CategoryRequest toCategoryDTO(Category category) {
        if (category == null) return null;
        return new CategoryRequest(category.getId(), category.getName(), category.getImageUrl());
    }

    public static List<String> toImageUrlList(List<ProductImage> images) {
        if (images == null) {
            return List.of();
        }
        return images.stream()
                .map(ProductImage::getImageUrl)
                .toList();
    }

    public static List<ProductRequest> toResponseList(List<Product> products) {
        return products.stream().map(ProductMapper::toDTORequest).collect(Collectors.toList());
    }
}
