package com.example.cartservice.service;

import com.example.cartservice.dto.CartDto;
import com.example.cartservice.jpa.CartEntity;

import java.time.LocalDate;
import java.util.Optional;

public interface CartService {
    CartDto createCart(CartDto orderDetails);
    Iterable<CartEntity> getCartsByUserId(String userId);
<<<<<<< Updated upstream
<<<<<<< Updated upstream
<<<<<<< Updated upstream
<<<<<<< HEAD
    CartDto getCartsByProductName(CartDto cartDto);
    void updateCart(CartDto cartDto);
=======
    CartDto getCartsByProductName(String userId,String productName);
    CartDto updateCart(CartDto cartDto);

    void deleteByUserId(String userId);

    void deleteByProductName(String productName);
>>>>>>> 308d72e012ab539beade09a33c416191ad380ff5
=======
    CartDto getCartsByProductName(CartDto cartDto);
    void updateCart(CartDto cartDto);
>>>>>>> Stashed changes
=======
    CartDto getCartsByProductName(CartDto cartDto);
    void updateCart(CartDto cartDto);
>>>>>>> Stashed changes
=======
    CartDto getCartsByProductName(CartDto cartDto);
    void updateCart(CartDto cartDto);
>>>>>>> Stashed changes
}
