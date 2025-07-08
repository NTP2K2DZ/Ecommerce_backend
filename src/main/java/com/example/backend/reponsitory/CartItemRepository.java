package com.example.backend.reponsitory;

import com.example.backend.entity.Cart;
import com.example.backend.entity.CartItem;
import com.example.backend.entity.ProductVariant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    CartItem findByCartAndProductVariant(Cart cart, ProductVariant productVariant);
}
