package com.example.cartservice.service;

import com.example.cartservice.dto.CartDto;
import com.example.cartservice.jpa.CartEntity;

<<<<<<< HEAD
import java.time.LocalDate;

public interface CartService {
    CartDto createCart(CartDto orderDetails);
    Iterable<CartEntity> getCartsByUserId(String userId);
=======
public interface CartService {
    CartDto createCart(CartDto cartDetails);
    CartDto getCartByOrderId(String orderId);
    Iterable<CartEntity> getCartByUserId(String userId);
>>>>>>> 610f58ab9daf17f514848a28b1b6c817dde405eb
}
