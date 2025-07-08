package com.example.backend.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
public class CategoryRequest {
    private String name;
    @JsonProperty("image_url")
    private String imageUrl;

    public CategoryRequest() {}

    public CategoryRequest(String name, String imageUrl) {
        this.name = name;
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
