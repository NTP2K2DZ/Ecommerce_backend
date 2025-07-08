package com.example.backend.mapper;

import com.example.backend.dto.response.CartResponse;
import com.example.backend.entity.Cart;
import com.example.backend.entity.Product;
import com.example.backend.entity.ProductVariantOptionValue;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CartMapper {

    @Mapping(target = "items", source = "cartItems")
    CartResponse toCartResponse(Cart cart);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "productVariantId", source = "productVariant.id")
    @Mapping(target = "productName", source = "productVariant.product.name")
    @Mapping(target = "productImage", expression = "java(getFirstProductImage(cartItem.getProductVariant().getProduct()))")
    @Mapping(target = "quantity", source = "quantity")
    @Mapping(target = "variantOptions", expression = "java(mapOptionValues(cartItem.getProductVariant().getOptionValues()))")
    CartResponse.CartItemResponse toCartItemResponse(com.example.backend.entity.CartItem cartItem);

    List<CartResponse.CartItemResponse> toCartItemResponseList(List<com.example.backend.entity.CartItem> cartItems);

    default List<CartResponse.VariantOptionResponse> mapOptionValues(List<ProductVariantOptionValue> optionValues) {
        if (optionValues == null) return List.of();
        return optionValues.stream()
                .map(ov -> new CartResponse.VariantOptionResponse(
                        ov.getOptionValue().getValue(),
                        ov.getOptionValue().getOption().getName()
                ))
                .toList();
    }

    default String getFirstProductImage(Product product) {
        if (product.getImages() != null && !product.getImages().isEmpty()) {
            return product.getImages().get(0).getImageUrl();
        }
        return null;
    }
}
