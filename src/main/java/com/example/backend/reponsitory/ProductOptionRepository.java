package com.example.backend.reponsitory;

import com.example.backend.entity.ProductOption;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductOptionRepository extends JpaRepository<ProductOption, Long> {
//    @Transactional
//    @Modifying
//    @Query("DELETE FROM ProductOption po WHERE po.product.id = :productId")
//    void deleteOptionByProductId(@Param("productId") Long productId);
    List<ProductOption> findByProductId(Long productId);
}
