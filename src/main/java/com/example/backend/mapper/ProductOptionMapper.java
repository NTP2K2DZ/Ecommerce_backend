package com.example.backend.mapper;

import com.example.backend.dto.product_option.ProductOptionCreationRequest;
import com.example.backend.dto.product_option.ProductOptionResponse;
import com.example.backend.entity.Product;
import com.example.backend.entity.ProductOption;

public class ProductOptionMapper {

    public static ProductOptionResponse toResponseDTO(ProductOption productOption) {
        return new ProductOptionResponse(
                productOption.getId(),
                productOption.getName(),
                productOption.getProduct().getId()
        );
    }

    public static ProductOption toEntityCreate(ProductOptionCreationRequest dto, Product product) {
        ProductOption option = new ProductOption();
        option.setName(dto.getName());
        option.setProduct(product);
        return option;
    }
}

