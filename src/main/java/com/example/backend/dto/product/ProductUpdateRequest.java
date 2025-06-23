package com.example.backend.dto.product;

import java.math.BigDecimal;
import java.util.List;

public class ProductUpdateRequest {
    private String name;
    private String description;
    private BigDecimal price;
    private Long categoryId;
    private Long brandId;
    private List<String> images;

    public ProductUpdateRequest() {}

    public ProductUpdateRequest(String name, String description, BigDecimal price,
                                Long categoryId, Long brandId, List<String> images) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.categoryId = categoryId;
        this.brandId = brandId;
        this.images = images;
    }

    // Getters & Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public Long getBrandId() {
        return brandId;
    }

    public void setBrandId(Long brandId) {
        this.brandId = brandId;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }
}
