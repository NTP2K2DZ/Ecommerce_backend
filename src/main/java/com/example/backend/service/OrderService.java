package com.example.backend.service;

import com.example.backend.dto.response.OrderResponse;
import com.example.backend.entity.Order;

import java.util.List;

public interface OrderService {
    List<OrderResponse> getAllOrders();
    List<OrderResponse> getOrdersByUserId(Long userId);
    OrderResponse getOrderById(Long orderId);
    Order createOrder(Long userId);
    OrderResponse updateOrderStatus(Long orderId, String status);
    OrderResponse cancelOrder(Long orderId, Long userId);
}
