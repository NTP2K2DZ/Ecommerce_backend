package com.example.backend.mapper;

import com.example.backend.dto.product_variant.ProductVariantCreationRequest;
import com.example.backend.dto.product_variant.ProductVariantResponse;
import com.example.backend.dto.product_variant.ProductVariantResponse.OptionValueDTO;
import com.example.backend.dto.product_variant.ProductVariantUpdateRequest;
import com.example.backend.entity.*;

import java.util.List;
import java.util.stream.Collectors;

public class ProductVariantMapper {

    public static ProductVariantResponse toResponseDTO(ProductVariant variant) {
        return new ProductVariantResponse(
                variant.getId(),
                variant.getSlug(),
                variant.getPrice(),
                variant.getQuantity(),
                toImageUrlList(variant.getImages()),
                toOptionValueDTOList(variant.getOptionValues())
        );
    }

    public static List<String> toImageUrlList(List<ProductVariantImage> images) {
        if (images == null) return List.of();
        return images.stream().map(ProductVariantImage::getImageUrl).toList();
    }

    public static List<OptionValueDTO> toOptionValueDTOList(List<ProductVariantOptionValue> optionValues) {
        if (optionValues == null) return List.of();
        return optionValues.stream().map(ov -> new OptionValueDTO(
                ov.getOptionValue().getId(),
                ov.getOptionValue().getValue(),
                ov.getOptionValue().getOption().getName()
        )).toList();
    }

    public static List<ProductVariantResponse> toResponseList(List<ProductVariant> variants) {
        return variants.stream().map(ProductVariantMapper::toResponseDTO).toList();
    }

    public static ProductVariant toEntityCreate(ProductVariantCreationRequest request, Product product) {
        ProductVariant variant = new ProductVariant();
        variant.setSlug(request.getSlug());
        variant.setPrice(request.getPrice());
        variant.setQuantity(request.getQuantity());
        variant.setProduct(product);
        return variant;
    }
}
