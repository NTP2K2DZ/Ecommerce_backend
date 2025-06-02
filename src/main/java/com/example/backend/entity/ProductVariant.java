//package com.example.backend.entity;
//
//import jakarta.persistence.*;
//
//import java.math.BigDecimal;
//import java.util.ArrayList;
//import java.util.List;
//
//@Entity
//@Table(name = "product_variant")
//public class ProductVariant {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//    @Column(unique = true)
//    private String sku;
//    private BigDecimal price;
//    private Integer quantity;
//    private String image;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "product_id", nullable = false)
//    private Product product;
//
//    @OneToMany(mappedBy = "variant", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<ProductVariantOptionValue> optionValues = new ArrayList<>();
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public String getSku() {
//        return sku;
//    }
//
//    public void setSku(String sku) {
//        this.sku = sku;
//    }
//
//    public BigDecimal getPrice() {
//        return price;
//    }
//
//    public void setPrice(BigDecimal price) {
//        this.price = price;
//    }
//
//    public Integer getQuantity() {
//        return quantity;
//    }
//
//    public void setQuantity(Integer quantity) {
//        this.quantity = quantity;
//    }
//
//    public String getImage() {
//        return image;
//    }
//
//    public void setImage(String image) {
//        this.image = image;
//    }
//
//    public Product getProduct() {
//        return product;
//    }
//
//    public void setProduct(Product product) {
//        this.product = product;
//    }
//
//    public List<ProductVariantOptionValue> getOptionValues() {
//        return optionValues;
//    }
//
//    public void setOptionValues(List<ProductVariantOptionValue> optionValues) {
//        this.optionValues = optionValues;
//    }
//}
