package com.example.backend.mapper;

import com.example.backend.dto.response.OrderItemResponse;
import com.example.backend.entity.OrderItem;
import com.example.backend.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrderItemMapper {

    @Mapping(target = "productId", source = "product.id")
    @Mapping(target = "productName", source = "product.name")
    @Mapping(target = "productImage", expression = "java(getFirstProductImage(orderItem.getProduct()))")
    @Mapping(target = "productVariant", source = "variant")
    OrderItemResponse toResponseDTO(OrderItem orderItem);

    default String getFirstProductImage(Product product) {
        if (product.getImages() != null && !product.getImages().isEmpty()) {
            return product.getImages().get(0).getImageUrl();
        }
        return null;
    }
}

