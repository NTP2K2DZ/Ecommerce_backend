package com.example.backend.service;

import com.example.backend.dto.request.AddItemRequest;
import com.example.backend.dto.response.CartResponse;
import com.example.backend.entity.Cart;

public interface CartService {
    CartResponse getUserCart(Long userId);
    CartResponse addItemToCart(Long userId, AddItemRequest request);
    void clearCart(Long userId);
}
