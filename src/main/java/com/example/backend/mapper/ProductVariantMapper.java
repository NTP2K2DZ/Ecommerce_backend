package com.example.backend.mapper;
import com.example.backend.dto.response.product.ProductVariantResponse;
import com.example.backend.entity.ProductVariant;
import com.example.backend.entity.ProductVariantImage;
import com.example.backend.entity.ProductVariantOptionValue;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductVariantMapper {
    @Mapping(target = "images", source = "images")
    @Mapping(target = "optionValues", source = "optionValues")
    ProductVariantResponse toResponseDTO(ProductVariant variant);
    List<ProductVariantResponse> toResponseList(List<ProductVariant> productVariants);
    default List<String> map(List<ProductVariantImage> images) {
        if (images == null) return null;
        return images.stream()
                .map(ProductVariantImage::getImageUrl)
                .toList();
    }

    default List<ProductVariantResponse.OptionValueDTO> mapOptionValues(List<ProductVariantOptionValue> optionValues) {
        if (optionValues == null) return List.of();
        return optionValues.stream()
                .map(ov -> new ProductVariantResponse.OptionValueDTO(
                        ov.getOptionValue().getId(),
                        ov.getOptionValue().getValue(),
                        ov.getOptionValue().getOption().getName()
                ))
                .toList();
    }
}
