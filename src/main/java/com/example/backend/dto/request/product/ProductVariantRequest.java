package com.example.backend.dto.request.product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductVariantRequest {
    private Double price;
    private Integer quantity;
    private String sku;
    private Long productId;
    private List<Long> optionValueIds;
    private List<String> images;
}
