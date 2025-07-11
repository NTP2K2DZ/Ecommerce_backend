package com.example.backend.controller;

import com.example.backend.dto.request.AddItemRequest;
import com.example.backend.dto.response.CartResponse;
import com.example.backend.entity.Cart;
import com.example.backend.entity.CartItem;
import com.example.backend.reponsitory.CartRepository;
import com.example.backend.service.AuthService;
import com.example.backend.service.CartItemService;
import com.example.backend.service.CartService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/user/cart")
public class CartController {
    private final CartService cartService;
    private final AuthService authService;
    private final CartItemService cartItemService;

    public CartController(CartService cartService, AuthService authService, CartItemService cartItemService) {
        this.cartService = cartService;
        this.authService = authService;
        this.cartItemService = cartItemService;
    }

    @GetMapping
    public ResponseEntity<CartResponse> getCart(@RequestHeader("Authorization") String jwt) {
        Long userId = authService.findUserByJwt(jwt).getId();
        CartResponse cart = cartService.getUserCart(userId);
        return ResponseEntity.ok(cart);
    }

    @PostMapping("/add-item")
    public ResponseEntity<CartResponse> addItemToCart(@RequestHeader("Authorization") String jwt,
                                              @RequestBody AddItemRequest request) {
        Long userId = authService.findUserByJwt(jwt).getId();
        CartResponse updatedCart = cartService.addItemToCart(userId, request);
        return ResponseEntity.ok(updatedCart);
    }

    @PatchMapping("/update-item")
    public ResponseEntity<CartItem> updateCartItem(@RequestHeader("Authorization") String jwt,
                                                   @RequestParam Long cartItemId,
                                                   @RequestParam int quantity) {
        Long userId = authService.findUserByJwt(jwt).getId();
        CartItem updatedItem = cartItemService.updateCartItem(cartItemId, quantity);
        return ResponseEntity.ok(updatedItem);
    }

    @DeleteMapping("/remove-item")
    public ResponseEntity<String> removeCartItem(@RequestHeader("Authorization") String jwt,
                                                 @RequestParam Long cartItemId) {
        Long userId = authService.findUserByJwt(jwt).getId();
        cartItemService.removeCartItem(cartItemId);
        return ResponseEntity.ok("Cart item removed successfully.");
    }
}
