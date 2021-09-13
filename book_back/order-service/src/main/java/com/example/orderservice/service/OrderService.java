package com.example.orderservice.service;

import com.example.orderservice.dto.OrderDto;
import com.example.orderservice.jpa.OrderEntity;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface OrderService {
    OrderDto createOrder(OrderDto orderDetails);
    OrderDto getOrderByOrderId(String orderId);
    Iterable<OrderEntity> getOrdersByUserId(String userId);
    Iterable<OrderEntity> getOrdersByUserIdAndOrderedAt(String userId, LocalDate sDate, LocalDate eDate);

    OrderDto createOrderByCart(OrderDto order);
}
