package com.example.backend.dto;

import com.example.backend.entity.ProductOptionValue;

public class ProductOptionValueCreationRequest {
    private Long id;
    private String value;
    private Long optionId;

    public ProductOptionValueCreationRequest(){}

    public ProductOptionValueCreationRequest(String value, Long id, Long optionId) {
        this.value = value;
        this.id = id;
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
