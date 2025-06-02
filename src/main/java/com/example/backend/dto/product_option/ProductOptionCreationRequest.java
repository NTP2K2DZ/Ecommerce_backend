package com.example.backend.dto.product_option;

public class ProductOptionCreationRequest {
    private Long id;
    private String name;
    private Long productId;

    public ProductOptionCreationRequest() {}

    public ProductOptionCreationRequest(Long id, String name, Long productId) {
        this.id = id;
        this.name = name;
        this.productId = productId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
