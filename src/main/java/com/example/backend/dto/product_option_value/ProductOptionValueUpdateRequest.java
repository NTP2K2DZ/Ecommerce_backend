package com.example.backend.dto.product_option_value;

public class ProductOptionValueUpdateRequest {
    private String value;
    private Long optionId;

    public ProductOptionValueUpdateRequest(){}

    public ProductOptionValueUpdateRequest(String value, Long optionId) {
        this.value = value;
        this.optionId = optionId;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Long getOptionId() {
        return optionId;
    }

    public void setOptionId(Long optionId) {
        this.optionId = optionId;
    }
}
