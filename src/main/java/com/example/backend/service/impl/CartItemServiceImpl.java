package com.example.backend.service.impl;

import com.example.backend.entity.Cart;
import com.example.backend.entity.CartItem;
import com.example.backend.entity.ProductVariant;
import com.example.backend.exception.ResourceNotFoundException;
import com.example.backend.reponsitory.CartItemRepository;
import com.example.backend.service.CartItemService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class CartItemServiceImpl implements CartItemService {

    private final CartItemRepository cartItemRepository;

    public CartItemServiceImpl(CartItemRepository cartItemRepository) {
        this.cartItemRepository = cartItemRepository;
    }

    @Override
    public CartItem updateCartItem(Long cartItemId, Integer quantity) {
        CartItem cartItem = cartItemRepository.findById(cartItemId)
                .orElseThrow(() -> new ResourceNotFoundException("CartItem not found with id: " + cartItemId));

        cartItem.setQuantity(quantity);

        return cartItemRepository.save(cartItem);
    }

    @Override
    @Transactional
    public CartItem findExistingCartItem(Cart cart, ProductVariant productVariant) {
        return cartItemRepository.findByCartAndProductVariant(cart, productVariant);
    }

    @Override
    @Transactional
    public void removeCartItem(Long cartItemId) {
        CartItem cartItem = cartItemRepository.findById(cartItemId)
                .orElseThrow(() -> new ResourceNotFoundException("CartItem not found with id: " + cartItemId));
        cartItemRepository.delete(cartItem);
    }

    @Override
    public CartItem findCartItemById(Long cartItemId) {
        return cartItemRepository.findById(cartItemId)
                .orElseThrow(() -> new ResourceNotFoundException("CartItem not found with id: " + cartItemId));
    }
}
