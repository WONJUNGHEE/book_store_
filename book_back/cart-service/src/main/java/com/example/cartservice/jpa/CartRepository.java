package com.example.cartservice.jpa;

import org.springframework.data.repository.CrudRepository;

public interface CartRepository extends CrudRepository<CartEntity, Long> {
    CartEntity findByCartId(String cartId);
    Iterable<CartEntity> findByUserId(String userId);
}