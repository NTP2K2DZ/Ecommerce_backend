package com.example.backend.mapper;

import com.example.backend.dto.response.product.ProductOptionValueResponse;
import com.example.backend.entity.ProductOptionValue;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductOptionValueMapper {

    @Mapping(target = "optionId", source = "option.id")
    ProductOptionValueResponse toResponseDTO(ProductOptionValue productOptionValue);
    List<ProductOptionValueResponse> toResponseDTOList(List<ProductOptionValue> productOptionValues);
}