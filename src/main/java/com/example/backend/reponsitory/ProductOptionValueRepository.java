package com.example.backend.reponsitory;

import com.example.backend.entity.ProductOptionValue;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductOptionValueRepository extends JpaRepository<ProductOptionValue, Long> {
    List<ProductOptionValue> findByOptionId(Long optionId);
}
