package com.example.backend.mapper;

import com.example.backend.dto.category.CategoryResponse;
import com.example.backend.dto.product.ProductCreationRequest;
import com.example.backend.dto.product.ProductResponse;
import com.example.backend.dto.product.ProductUpdateRequest;
import com.example.backend.entity.*;
import java.util.List;
import java.util.stream.Collectors;

public class ProductMapper {
    public static ProductResponse toResponseDTO(Product product) {
        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getSlug(),
                product.getDescription(),
                product.getPrice(),
                product.getCategory().getName(),
                product.getBrand().getName(),
                toImageUrlList(product.getImages())
        );
    }

    public static Product toEntityCreate(ProductCreationRequest request, Category category, Brand brand) {
        Product product = new Product();
        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());
        product.setCategory(category);
        product.setBrand(brand);
        return product;
    }

    public static Product toEntityUpdate(ProductUpdateRequest request, Category category) {
        Product product = new Product();
        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());
        product.setCategory(category);
        return product;
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
