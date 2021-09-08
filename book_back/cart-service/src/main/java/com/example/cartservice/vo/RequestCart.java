package com.example.cartservice.vo;

import lombok.Data;

@Data
public class RequestCart {
    private String productId;
    private Integer qty;
    private Integer unitPrice;
    private String instance_Id;
}