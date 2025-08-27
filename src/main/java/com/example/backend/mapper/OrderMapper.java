package com.example.backend.mapper;

import com.example.backend.dto.response.OrderResponse;
import com.example.backend.entity.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {OrderItemMapper.class})
public interface OrderMapper {
    @Mapping(source = "orderItems", target = "items")
    OrderResponse toResponseDTO(Order order);
    List<OrderResponse> toResponseDTOList(List<Order> orders);
}
