package com.example.backend.dto.category;

public class CategoryUpdateRequest {
    private String name;
    private String image_url;

    public CategoryUpdateRequest() {}

    public CategoryUpdateRequest(String name, String image_url) {
        this.name = name;
        this.image_url = image_url;
    }

    public String getName() {
        return name;
    }

    public String getImageUrl() {
        return image_url;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImageUrl(String imageUrl) {
        this.image_url = image_url;
    }
}
