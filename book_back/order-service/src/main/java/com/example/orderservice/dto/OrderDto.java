package com.example.orderservice.dto;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

@Data
public class OrderDto implements Serializable {
    private String productId;
    private String productName;
    private Integer qty;
    private Integer unitPrice;
    private Integer totalPrice;
    private String orderId;
    private String userId;
    private String category;
    private LocalDate orderedAt;
}
