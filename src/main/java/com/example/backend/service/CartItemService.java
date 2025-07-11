package com.example.backend.service;

import com.example.backend.entity.Cart;
import com.example.backend.entity.CartItem;
import com.example.backend.entity.ProductVariant;

public interface CartItemService {
    CartItem updateCartItem(Long cartItemId, Integer quantity);

    CartItem findExistingCartItem(Cart cart, ProductVariant productVariant);

    void removeCartItem(Long cartItemId);

    CartItem findCartItemById(Long cartItemId);
}
