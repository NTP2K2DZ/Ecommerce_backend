package com.example.backend.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AddItemRequest {
    @JsonProperty("product_variant_id")
    private Long productVariantId;
    private Integer quantity;

    public AddItemRequest() {
    }

    public AddItemRequest(Long productVariantId, Integer quantity) {
        this.productVariantId = productVariantId;
        this.quantity = quantity;
    }

    public Long getProductVariantId() {
        return productVariantId;
    }

    public void setProductVariantId(Long productVariantId) {
        this.productVariantId = productVariantId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
