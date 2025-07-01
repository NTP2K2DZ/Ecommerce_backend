package com.example.backend.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CategoryResponse {
    private Long id;
    private String name;
    @JsonProperty("image_url")
    private String imageUrl;
    private String slug;

    public CategoryResponse() {}

    public CategoryResponse(Long id, String name, String imageUrl, String slug) {
        this.id = id;
        this.name = name;
        this.imageUrl = imageUrl;
        this.slug = slug;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }
}
