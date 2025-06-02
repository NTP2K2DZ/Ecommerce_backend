package com.example.backend.mapper;

import com.example.backend.dto.product_option.ProductOptionCreationRequest;
import com.example.backend.entity.ProductOption;

public class ProductOptionMapper {

    // Entity -> DTO
    public static ProductOptionCreationRequest toDTO(ProductOption option) {
        ProductOptionCreationRequest dto = new ProductOptionCreationRequest();
        dto.setId(option.getId());
        dto.setName(option.getName());
        dto.setProductId(option.getProduct().getId());
        return dto;
    }

    // DTO -> Entity
    public static ProductOption toEntity(ProductOptionCreationRequest dto) {
        ProductOption option = new ProductOption();
        option.setId(dto.getId());
        option.setName(dto.getName());
        return option;
    }
}

