package com.example.backend.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "product_option_value")
public class ProductOptionValue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String value;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "option_id", nullable = false)
    private ProductOption option;

    @OneToMany(mappedBy = "optionValue", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProductVariantOptionValue> variantOptionValues = new ArrayList<>();

    public ProductOptionValue(){}

    public ProductOptionValue(String value, ProductOption option) {
        this.value = value;
        this.option = option;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ProductOption getProductOption() {
        return option;
    }

    public void setProductOption(ProductOption option) {
        this.option = option;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
    public ProductOption getOption() {
        return option;
    }

    public void setOption(ProductOption option) {
        this.option = option;
    }

    public List<ProductVariantOptionValue> getVariantOptionValues() {
        return variantOptionValues;
    }

    public void setVariantOptionValues(List<ProductVariantOptionValue> variantOptionValues) {
        this.variantOptionValues = variantOptionValues;
    }
}
