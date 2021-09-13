package com.example.cartservice.vo;

import lombok.Data;

@Data
public class Requestcart {
    private String productId;
    private String productName;
    private Integer qty;
    private Integer unitPrice;
    private String instance_Id;
}

