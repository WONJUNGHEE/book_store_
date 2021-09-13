package com.example.cartservice.service;

import com.example.cartservice.dto.CartDto;
import com.example.cartservice.jpa.CartEntity;

import java.time.LocalDate;
import java.util.Optional;

public interface CartService {
    CartDto createCart(CartDto orderDetails);
    Iterable<CartEntity> getCartsByUserId(String userId);
    CartDto getCartsByProductName(CartDto cartDto);
    void updateCart(CartDto cartDto);
}
