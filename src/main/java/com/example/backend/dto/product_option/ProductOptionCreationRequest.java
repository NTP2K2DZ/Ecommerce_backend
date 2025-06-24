package com.example.backend.dto.product_option;

public class ProductOptionCreationRequest {
    private String name;
    private Long productId;

    public ProductOptionCreationRequest() {}

    public ProductOptionCreationRequest(String name, Long productId) {
        this.name = name;
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }
}
