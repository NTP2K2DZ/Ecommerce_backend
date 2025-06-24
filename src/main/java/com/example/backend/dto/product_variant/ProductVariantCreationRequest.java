package com.example.backend.dto.product_variant;

import java.math.BigDecimal;
import java.util.List;

public class ProductVariantCreationRequest {
    private BigDecimal price;
    private Integer quantity;
    private String sku;
    private Long productId;
    private List<Long> optionValueIds;
    private List<String> images;

    public ProductVariantCreationRequest() {}

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

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
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

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }
}
