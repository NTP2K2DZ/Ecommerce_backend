package com.example.backend.dto.response;

import java.util.List;

public class CartResponse {
    private Long id;
    private List<CartItemResponse> items;
    private double totalPrice;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<CartItemResponse> getItems() {
        return items;
    }

    public void setItems(List<CartItemResponse> items) {
        this.items = items;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public static class CartItemResponse {
        private Long id;
        private Long productVariantId;
        private String productName;
        private List<VariantOptionResponse> variantOptions;
        private String productImage;
        private int quantity;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public Long getProductVariantId() {
            return productVariantId;
        }

        public void setProductVariantId(Long productVariantId) {
            this.productVariantId = productVariantId;
        }

        public String getProductName() {
            return productName;
        }

        public void setProductName(String productName) {
            this.productName = productName;
        }

        public List<VariantOptionResponse> getVariantOptions() {
            return variantOptions;
        }

        public void setVariantOptions(List<VariantOptionResponse> variantOptions) {
            this.variantOptions = variantOptions;
        }

        public String getProductImage() {
            return productImage;
        }

        public void setProductImage(String productImage) {
            this.productImage = productImage;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }
    }

    public static class VariantOptionResponse {
        private String optionName;
        private String optionValue;

        public VariantOptionResponse() {}

        public VariantOptionResponse(String optionName, String optionValue) {
            this.optionName = optionName;
            this.optionValue = optionValue;
        }

        public String getOptionName() {
            return optionName;
        }

        public void setOptionName(String optionName) {
            this.optionName = optionName;
        }

        public String getOptionValue() {
            return optionValue;
        }

        public void setOptionValue(String optionValue) {
            this.optionValue = optionValue;
        }
    }
}
