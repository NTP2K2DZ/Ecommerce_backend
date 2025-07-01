package com.example.backend.reponsitory;

import com.example.backend.entity.Product;
import com.example.backend.entity.ProductVariant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductVariantRepository extends JpaRepository<ProductVariant, Long> {
    List<ProductVariant> findByProductId (Long productId);
    boolean existsBySku(String sku);
}
