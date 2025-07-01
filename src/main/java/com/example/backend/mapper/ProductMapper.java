package com.example.backend.mapper;

import com.example.backend.dto.response.product.ProductResponse;
import com.example.backend.entity.*;
import org.mapstruct.*;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    @Mapping(target = "categoryName", source = "category.name")
    @Mapping(target = "brandName", source = "brand.name")
    @Mapping(target = "images", source = "images", qualifiedByName = "mapImagesToUrls")
    ProductResponse toResponseDTO(Product product);

    List<ProductResponse> toResponseList(List<Product> products);

    @Named("mapImagesToUrls")
    static List<String> mapImagesToUrls(List<ProductImage> images) {
        if (images == null) {
            return List.of();
        }
        return images.stream()
                .map(ProductImage::getImageUrl)
                .collect(Collectors.toList());
    }
}
