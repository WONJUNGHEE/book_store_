package com.example.cartservice.jpa;

import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;

public interface CartRepository extends CrudRepository<CartEntity, Long> {
    Iterable<CartEntity> findByUserId(String userId);
    CartEntity findByUserIdAndProductName(String userId,String productName);
}
