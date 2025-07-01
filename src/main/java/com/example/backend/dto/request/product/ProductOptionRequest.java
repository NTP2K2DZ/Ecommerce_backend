package com.example.backend.dto.request.product;

public class ProductOptionRequest {
    private String name;
    private Long productId;

    public ProductOptionRequest() {}

    public ProductOptionRequest(String name, Long productId) {
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
