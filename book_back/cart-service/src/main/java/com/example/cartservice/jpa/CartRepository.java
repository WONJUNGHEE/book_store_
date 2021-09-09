package com.example.cartservice.jpa;

import org.springframework.data.repository.CrudRepository;

<<<<<<< HEAD
import java.time.LocalDate;

public interface CartRepository extends CrudRepository<CartEntity, Long> {
    Iterable<CartEntity> findByUserId(String userId);
}
=======
public interface CartRepository extends CrudRepository<CartEntity, Long> {
    CartEntity findByCartId(String cartId);
    Iterable<CartEntity> findByUserId(String userId);
}
>>>>>>> 610f58ab9daf17f514848a28b1b6c817dde405eb
