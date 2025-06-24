package com.example.backend.dto.product;

import java.math.BigDecimal;
import java.util.List;

public class ProductResponse {
    private Long id;
    private String name;
    private String slug;
    private String description;
    private BigDecimal price;
    private String categoryName;
    private String brandName;
    private List<String> images;

    public ProductResponse() {}

    public ProductResponse(Long id, String name, String slug, String description, BigDecimal price,
                           String categoryName, String brandName, List<String> images) {
        this.id = id;
        this.name = name;
        this.slug = slug;
        this.description = description;
        this.price = price;
        this.categoryName = categoryName;
        this.brandName = brandName;
        this.images = images;
    }

    // Getters & Setters

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

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
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

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }
}
