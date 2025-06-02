//package com.example.backend.entity;
//
//import jakarta.persistence.*;
//
//@Entity
//@Table
//public class ProductVariantOptionValue {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @ManyToOne
//    @JoinColumn(name = "variant_id", nullable = false)
//    private ProductVariant variant;
//
//    @ManyToOne
//    @JoinColumn(name = "option_value_id", nullable = false)
//    private ProductOptionValue optionValue;
//}
