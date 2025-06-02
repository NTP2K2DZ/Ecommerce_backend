package com.example.backend.dto.product;

import com.example.backend.dto.product_image.ProductImageCreationRequest;
import com.example.backend.dto.category.CategoryRequest;
import com.example.backend.dto.product_image.ProductImageRequest;
import com.example.backend.entity.ProductImage;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class ProductRequest {
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private Integer quantity;
    private CategoryRequest category;
    private List<String> images;

    public ProductRequest() {}

    public ProductRequest(Long id, String name, String description, BigDecimal price, Integer quantity,
                          CategoryRequest category, List<String> images) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
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

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public CategoryRequest getCategory() {
        return category;
    }

    public void setCategory(CategoryRequest category) {
        this.category = category;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }
}
