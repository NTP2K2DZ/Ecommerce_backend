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
    private ProductOption productOption;

//    @OneToMany(mappedBy = "optionValue", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<ProductVariantOptionValue> variantValues = new ArrayList<>();

    public ProductOptionValue(){}

    public ProductOptionValue(String value, ProductOption productOption) {
        this.value = value;
        this.productOption = productOption;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ProductOption getProductOption() {
        return productOption;
    }

    public void setProductOption(ProductOption productOption) {
        this.productOption = productOption;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

//    public List<ProductVariantOptionValue> getVariantValues() {
//        return variantValues;
//    }
//
//    public void setVariantValues(List<ProductVariantOptionValue> variantValues) {
//        this.variantValues = variantValues;
//    }
}
