package com.example.backend.dto.product;

import com.example.backend.dto.category.CategoryCreationRequest;
import com.example.backend.dto.category.CategoryRequest;
import com.example.backend.dto.product_image.ProductImageCreationRequest;

import java.math.BigDecimal;
import java.util.List;

public class ProductUpdateRequest {
    private String name;
    private String description;
    private BigDecimal price;
    private Integer quantity;
    private CategoryRequest category;
    private List<String> images;

    public  ProductUpdateRequest() {}

    public ProductUpdateRequest(String name, String description, BigDecimal price, Integer quantity,
                                CategoryRequest category, List<String> images) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
        this.category =category;
        this.images = images;
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
