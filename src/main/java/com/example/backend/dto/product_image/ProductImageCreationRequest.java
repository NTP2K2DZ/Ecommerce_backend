package com.example.backend.dto.product_image;

public class ProductImageCreationRequest {
    private String imageUrl;

    public ProductImageCreationRequest() {}

    public ProductImageCreationRequest(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
