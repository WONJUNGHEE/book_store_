package com.example.userservice.vo;

import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

@Data
public class ResponseOrder {
    private String productId;
    private Integer qty;
    private Integer unitPrice;
    private Integer totalPrice;
    private LocalDate orderedAt;

    private String orderId;
}
