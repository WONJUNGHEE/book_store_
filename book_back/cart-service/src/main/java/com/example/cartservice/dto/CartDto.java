package com.example.cartservice.dto;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

@Data
public class CartDto implements Serializable {
    private String productId;
    private String productName;
    private Integer qty;
    private Integer unitPrice;
    private Integer totalPrice;
    private String userId;
    private String cartId;
    private String category;
    private String src;
    private String detail;
    private Integer find;
}
