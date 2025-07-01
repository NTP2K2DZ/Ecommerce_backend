package com.example.backend.dto.response.product;

import java.math.BigDecimal;
import java.util.List;

public class ProductVariantResponse {
    private Long id;
    private String sku;
    private BigDecimal price;
    private Integer quantity;
    private List<String> images;
    private List<OptionValueDTO> optionValues;

    public static class OptionValueDTO {
        private Long id;
        private String value;
        private String optionName;

        public OptionValueDTO() {}

        public OptionValueDTO(Long id, String value, String optionName) {
            this.id = id;
            this.value = value;
            this.optionName = optionName;
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public String getOptionName() {
            return optionName;
        }

        public void setOptionName(String optionName) {
            this.optionName = optionName;
        }
    }

    public ProductVariantResponse() {}

    public ProductVariantResponse(Long id, BigDecimal price, Integer quantity, String sku, List<String> images, List<OptionValueDTO> optionValues) {
        this.id = id;
        this.price = price;
        this.quantity = quantity;
        this.sku = sku;
        this.images = images;
        this.optionValues = optionValues;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public List<OptionValueDTO> getOptionValues() {
        return optionValues;
    }

    public void setOptionValues(List<OptionValueDTO> optionValues) {
        this.optionValues = optionValues;
    }
}
