package com.example.cartservice.jpa;

import com.example.cartservice.dto.CartDto;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.Optional;

public interface CartRepository extends CrudRepository<CartEntity, Long> {
    Iterable<CartEntity> findByUserId(String userId);
    Optional<CartEntity> findByUserIdAndProductName(String userId, String productName);
    void deleteByUserId(String userId);

    void deleteByProductName(String productName);
}
