package com.example.backend.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BrandResponse {

    private Long id;
    private String name;
    private String slug;
    @JsonProperty("logo_url")
    private String logoUrl;

    public BrandResponse() {
    }

    public BrandResponse(Long id, String name, String slug, String logoUrl) {
        this.id = id;
        this.name = name;
        this.slug = slug;
        this.logoUrl = logoUrl;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }
}
