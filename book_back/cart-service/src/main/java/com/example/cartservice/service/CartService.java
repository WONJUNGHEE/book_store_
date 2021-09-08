package com.example.cartservice.service;

import com.example.cartservice.dto.CartDto;
import com.example.cartservice.jpa.CartEntity;

public interface CartService {
    CartDto createCart(CartDto cartDetails);
    CartDto getCartByOrderId(String orderId);
    Iterable<CartEntity> getCartByUserId(String userId);
}
