package com.example.cartservice.jpa;

import com.example.cartservice.dto.CartDto;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.Optional;

public interface CartRepository extends CrudRepository<CartEntity, Long> {
    Iterable<CartEntity> findByUserId(String userId);
<<<<<<< HEAD
    CartEntity findByUserIdAndProductName(String userId,String productName);

    void deleteByUserId(String userId);

    void deleteByProductName(String productName);
=======
    Optional<CartEntity> findByUserIdAndProductName(String userId, String productName);
>>>>>>> c8651ebec78bfe181ea87b434f295dc1f1bcea2b
}
