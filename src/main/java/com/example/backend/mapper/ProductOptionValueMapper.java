package com.example.backend.mapper;

import com.example.backend.dto.product_option_value.ProductOptionValueCreationRequest;
import com.example.backend.dto.product_option_value.ProductOptionValueResponse;
import com.example.backend.dto.product_option_value.ProductOptionValueUpdateRequest;
import com.example.backend.entity.ProductOption;
import com.example.backend.entity.ProductOptionValue;

public class ProductOptionValueMapper {

    public static ProductOptionValueResponse toResponseDTO (ProductOptionValue productOptionValue) {
        return new ProductOptionValueResponse(
                productOptionValue.getId(),
                productOptionValue.getValue(),
                productOptionValue.getProductOption().getId()
        );
    }

    public static ProductOptionValue toEntityCreate(ProductOptionValueCreationRequest request, ProductOption option) {
        ProductOptionValue optionValue = new ProductOptionValue();
        optionValue.setValue(request.getValue());
        optionValue.setProductOption(option);
        return optionValue;
    }
}
