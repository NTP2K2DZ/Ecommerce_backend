package com.example.backend.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "product_variant_option_value")
public class ProductVariantOptionValue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "variant_id", nullable = false)
    private ProductVariant variant;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "option_value_id", nullable = false)
    private ProductOptionValue optionValue;

    public ProductVariantOptionValue() {}

    public ProductVariantOptionValue(ProductVariant variant, ProductOptionValue optionValue) {
        this.variant = variant;
        this.optionValue = optionValue;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ProductVariant getVariant() {
        return variant;
    }

    public void setVariant(ProductVariant variant) {
        this.variant = variant;
    }

    public ProductOptionValue getOptionValue() {
        return optionValue;
    }

    public void setOptionValue(ProductOptionValue optionValue) {
        this.optionValue = optionValue;
    }
}
