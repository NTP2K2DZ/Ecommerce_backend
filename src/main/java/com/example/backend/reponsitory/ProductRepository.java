package com.example.backend.reponsitory;

import com.example.backend.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByCategoryId(Long categoryId);
    List<Product> findByBrandId(Long brandId);
    boolean existsBySlug(String slug);
    boolean existsByCategoryId(Long categoryId);
    boolean existsByBrandId(Long brandId);
}
