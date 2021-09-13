package com.example.cartservice.jpa;

import com.example.cartservice.dto.CartDto;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.Optional;

public interface CartRepository extends CrudRepository<CartEntity, Long> {
    Iterable<CartEntity> findByUserId(String userId);
<<<<<<< Updated upstream
<<<<<<< Updated upstream
<<<<<<< Updated upstream
<<<<<<< HEAD
    Optional<CartEntity> findByUserIdAndProductName(String userId, String productName);
=======
    CartEntity findByUserIdAndProductName(String userId,String productName);

    void deleteByUserId(String userId);

    void deleteByProductName(String productName);
>>>>>>> 308d72e012ab539beade09a33c416191ad380ff5
=======
    Optional<CartEntity> findByUserIdAndProductName(String userId, String productName);
>>>>>>> Stashed changes
=======
    Optional<CartEntity> findByUserIdAndProductName(String userId, String productName);
>>>>>>> Stashed changes
=======
    Optional<CartEntity> findByUserIdAndProductName(String userId, String productName);
>>>>>>> Stashed changes
}
