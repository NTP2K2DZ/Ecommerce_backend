package com.example.backend.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BrandRequest {

    private String name;
    @JsonProperty("logo_url")
    private String logoUrl;

    public BrandRequest() {
    }

    public BrandRequest(String name, String logoUrl) {
        this.name = name;
        this.logoUrl = logoUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }
}
