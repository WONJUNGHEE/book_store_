package com.example.cartservice.vo;

import lombok.Data;

@Data
public class RequestCart {
    private String productId;
    private String productName;
    private Integer qty;
    private Integer unitPrice;
}
