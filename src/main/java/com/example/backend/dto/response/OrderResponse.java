package com.example.backend.dto.response;

import com.example.backend.dto.response.OrderItemResponse;
import com.example.backend.entity.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponse {

    private Long id;
    private String receiverName;
    private String shippingAddress;
    private LocalDateTime orderDate;
    private OrderStatus status;

    private String paymentMethod;
    private String paymentStatus;

    private Double totalPrice;
    private String note;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private List<OrderItemResponse> items;
    private AddressResponse address;
}
