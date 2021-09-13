package com.example.orderservice.jpa;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface OrderRepository extends CrudRepository<OrderEntity, Long> {
    OrderEntity findByOrderId(String orderId);
    Iterable<OrderEntity> findByUserId(String userId);
    Iterable<OrderEntity> findByUserIdAndOrderedAtBetween(String userId, LocalDate sDate, LocalDate eDate);

//    @Query("select o from OrderEntity o where o.userId = :user_id and o.createdAt between :sDate and :eDate")
//    List<OrderEntity> findByDate(@Param("user_id") String userId, @Param("sDate") LocalDate sDate, @Param("eDate") LocalDate eDate);
}
