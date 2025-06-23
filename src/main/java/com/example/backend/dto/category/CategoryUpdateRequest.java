package com.example.backend.dto.category;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CategoryUpdateRequest {
    private String name;
    @JsonProperty("image_url")
    private String imageUrl;

    public CategoryUpdateRequest() {}

    public CategoryUpdateRequest(String name, String imageUrl) {
        this.name = name;
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
