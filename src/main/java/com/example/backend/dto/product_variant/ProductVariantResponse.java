package com.example.backend.dto.product_variant;

import java.math.BigDecimal;
import java.util.List;

public class ProductVariantResponse {
    private Long id;
    private String slug;
    private BigDecimal price;
    private Integer quantity;
    private List<String> image_url;
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

    public ProductVariantResponse(Long id, String slug, BigDecimal price, Integer quantity, List<String> image_url, List<OptionValueDTO> optionValues) {
        this.id = id;
        this.slug = slug;
        this.price = price;
        this.quantity = quantity;
        this.image_url = image_url;
        this.optionValues = optionValues;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public List<String> getImageUrl() {
        return image_url;
    }

    public void setImageUrl(List<String> image_url) {
        this.image_url = image_url;
    }

    public List<OptionValueDTO> getOptionValues() {
        return optionValues;
    }

    public void setOptionValues(List<OptionValueDTO> optionValues) {
        this.optionValues = optionValues;
    }
}
