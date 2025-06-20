package com.example.backend.dto.product_variant;

import java.math.BigDecimal;
import java.util.List;

public class ProductVariantUpdateRequest {
    private String slug;
    private BigDecimal price;
    private Integer quantity;
    private Long productId;
    private List<Long> optionValueIds;
    private List<String> image_url;

    public ProductVariantUpdateRequest() {}

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
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

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public List<Long> getOptionValueIds() {
        return optionValueIds;
    }

    public void setOptionValueIds(List<Long> optionValueIds) {
        this.optionValueIds = optionValueIds;
    }

    public List<String> getImageUrl() {
        return image_url;
    }

    public void setImageUrl(List<String> image_url) {
        this.image_url = image_url;
    }
}
