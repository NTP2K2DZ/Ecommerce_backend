package com.example.backend.mapper;

import com.example.backend.dto.ProductOptionValueCreationRequest;
import com.example.backend.entity.ProductOptionValue;

public class ProductOptionValueMapper {

    // Entity -> DTO
    public static ProductOptionValueCreationRequest toDTO(ProductOptionValue optionValue) {
        ProductOptionValueCreationRequest dto = new ProductOptionValueCreationRequest();
        dto.setId(optionValue.getId());
        dto.setValue(optionValue.getValue());
        dto.setOptionId(optionValue.getProductOption().getId());
        return dto;
    }

    // DTO -> Entity
    public static ProductOptionValue toEntity(ProductOptionValueCreationRequest dto) {
        ProductOptionValue optionValue = new ProductOptionValue();
        optionValue.setId(dto.getId());
        optionValue.setValue(dto.getValue());
        return optionValue;
    }
}
