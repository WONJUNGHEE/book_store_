package com.example.cartservice.service;

import com.example.cartservice.dto.CartDto;
import com.example.cartservice.jpa.CartEntity;

import java.time.LocalDate;

public interface CartService {
    CartDto createCart(CartDto orderDetails);
    Iterable<CartEntity> getCartsByUserId(String userId);
    CartDto getCartsByProductName(String userId,String productName);
    CartDto updateCart(CartDto cartDto);
    void deleteByUserId(String userId);
}
