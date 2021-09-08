package com.example.cartservice.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Payload {
    private String cart_id;
    private String user_id;
    private String product_id;
    private String instance_id;
    private int qty;
    private int total_price;
    private int unit_price;

}