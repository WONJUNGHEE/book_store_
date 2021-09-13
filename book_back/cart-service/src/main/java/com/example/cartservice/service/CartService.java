package com.example.cartservice.service;

import com.example.cartservice.dto.CartDto;
import com.example.cartservice.jpa.CartEntity;

import java.time.LocalDate;
import java.util.Optional;

public interface CartService {
    CartDto createCart(CartDto orderDetails);
    Iterable<CartEntity> getCartsByUserId(String userId);
<<<<<<< HEAD
    CartDto getCartsByProductName(String userId,String productName);
    CartDto updateCart(CartDto cartDto);

    void deleteByUserId(String userId);

    void deleteByProductName(String productName);
=======
    CartDto getCartsByProductName(CartDto cartDto);
    void updateCart(CartDto cartDto);
>>>>>>> c8651ebec78bfe181ea87b434f295dc1f1bcea2b
}
