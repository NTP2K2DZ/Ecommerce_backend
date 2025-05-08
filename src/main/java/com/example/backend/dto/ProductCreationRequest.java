package com.example.backend.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class ProductCreationRequest {
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private int quantity;
    private LocalDateTime createdAt;
    private CategoryCreationRequest category;
    private List<ProductImageCreationRequest> images;

    public ProductCreationRequest() {}

    public ProductCreationRequest(Long id, String name, String description, BigDecimal price, int quantity,
                      LocalDateTime createdAt, CategoryCreationRequest category, List<ProductImageCreationRequest> images) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
        this.createdAt = createdAt;
        this.category = category;
        this.images = images;
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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public CategoryCreationRequest getCategory() {
        return category;
    }

    public void setCategory(CategoryCreationRequest category) {
        this.category = category;
    }

    public List<ProductImageCreationRequest> getImages() {
        return images;
    }

    public void setImages(List<ProductImageCreationRequest> images) {
        this.images = images;
    }
}
