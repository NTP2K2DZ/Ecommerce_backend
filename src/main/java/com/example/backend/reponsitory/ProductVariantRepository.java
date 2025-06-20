package com.example.backend.reponsitory;

import com.example.backend.entity.Product;
import com.example.backend.entity.ProductVariant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductVariantRepository extends JpaRepository<ProductVariant, Long> {
    List<ProductVariant> findByProductId (Long productId);
}
