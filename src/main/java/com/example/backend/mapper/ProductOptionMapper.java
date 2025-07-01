package com.example.backend.mapper;


import com.example.backend.dto.response.product.ProductOptionResponse;
import com.example.backend.entity.ProductOption;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductOptionMapper {
    @Mapping(target = "productId", source = "product.id")
    ProductOptionResponse toResponseDTO(ProductOption productOption);
    List<ProductOptionResponse> toResponseDTOList(List<ProductOption> productOptions);
}

