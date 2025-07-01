package com.example.backend.dto.response.product;

public class ProductOptionValueResponse {
    private Long id;
    private String value;
    private Long optionId;

    public ProductOptionValueResponse() {};

    public ProductOptionValueResponse(Long id, String value, Long optionId) {
        this.id = id;
        this.value = value;
        this.optionId = optionId;
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

    public Long getOptionId() {
        return optionId;
    }

    public void setOptionId(Long optionId) {
        this.optionId = optionId;
    }
}
