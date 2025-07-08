package com.example.backend.service.impl;

import com.example.backend.dto.request.AddItemRequest;
import com.example.backend.dto.response.CartResponse;
import com.example.backend.entity.*;
import com.example.backend.exception.ResourceNotFoundException;
import com.example.backend.mapper.CartMapper;
import com.example.backend.reponsitory.CartItemRepository;
import com.example.backend.reponsitory.CartRepository;
import com.example.backend.reponsitory.ProductVariantRepository;
import com.example.backend.reponsitory.UserRepository;
import com.example.backend.service.CartItemService;
import com.example.backend.service.CartService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final CartMapper cartMapper;
    private final CartItemService cartItemService;
    private final ProductVariantRepository productVariantRepository;
    private final UserRepository userRepository;

    public CartServiceImpl(CartRepository cartRepository, ProductVariantRepository productVariantRepository, UserRepository userRepository, CartMapper cartMapper, CartItemService cartItemService) {
        this.cartRepository = cartRepository;
        this.productVariantRepository = productVariantRepository;
        this.userRepository = userRepository;
        this.cartMapper = cartMapper;
        this.cartItemService = cartItemService;
    }

    private Cart createCart(Long userId) {
        return cartRepository.findByUserId(userId)
                .orElseGet(() -> {
                    User user = userRepository.findById(userId)
                            .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));

                    Cart newCart = new Cart();
                    newCart.setUser(user);
                    newCart.setCartItems(new ArrayList<>());
                    newCart.setTotalPrice(0.0);
                    return cartRepository.save(newCart);
                });
    }

    @Override
    public CartResponse getUserCart(Long userId) {
        Cart cart = createCart(userId);

        double totalPrice = 0.0;
        List<CartItem> cartItems = cart.getCartItems();

        if (cartItems != null) {
            for (CartItem cartItem : cartItems) {
                totalPrice += cartItem.getProductVariant().getPrice() * cartItem.getQuantity();
            }
        }
        cart.setTotalPrice(totalPrice);
        return cartMapper.toCartResponse(cartRepository.save(cart));
    }


    @Override
    @Transactional
    public CartResponse addItemToCart(Long userId, AddItemRequest request) {

        Cart cart = createCart(userId);

        ProductVariant variant = productVariantRepository.findById(request.getProductVariantId())
                .orElseThrow(() -> new ResourceNotFoundException("Product variant not found"));

        CartItem existingItem = cartItemService.findExistingCartItem(cart, variant);
        if (existingItem != null) {
            existingItem.setQuantity(existingItem.getQuantity() + request.getQuantity());
        } else {
            CartItem newItem = new CartItem();
            newItem.setCart(cart);
            newItem.setProductVariant(variant);
            newItem.setQuantity(request.getQuantity());
            cart.getCartItems().add(newItem);
        }

        double totalPrice = cart.getCartItems().stream()
                .mapToDouble(item -> item.getProductVariant().getPrice() * item.getQuantity())
                .sum();

        cart.setTotalPrice(totalPrice);

        Cart savedCart = cartRepository.save(cart);
        return cartMapper.toCartResponse(savedCart);
    }

    @Override
    @Transactional
    public void clearCart(Long userId) {
        Cart cart = cartRepository.findByUserId(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Cart not found for user id: " + userId));

        cart.getCartItems().clear();
        cart.setTotalPrice(0.0);
        cartRepository.save(cart);
    }
}
