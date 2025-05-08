package com.example.backend.dto;

public class ProductImageCreationRequest {
    private Long id;
    private String imageUrl;

    public ProductImageCreationRequest() {}

    public ProductImageCreationRequest(Long id, String imageUrl) {
        this.id = id;
        this.imageUrl = imageUrl;
    }

    public Long getId() {
        return id;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
