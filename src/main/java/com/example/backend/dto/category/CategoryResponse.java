package com.example.backend.dto.category;

public class CategoryResponse {
    private Long id;
    private String name;
    private String image_url;

    public CategoryResponse() {}

    public CategoryResponse(Long id, String name, String image_url) {
        this.id = id;
        this.name = name;
        this.image_url = image_url;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getImageUrl() {
        return image_url;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImageUrl(String imageUrl) {
        this.image_url = image_url;
    }
}
