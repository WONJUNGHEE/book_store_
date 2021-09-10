package com.example.orderservice.vo;

import com.example.orderservice.dto.CartDto;
import lombok.Data;

import java.util.List;

@Data
public class RequestOrder {
    private String productId;
    private Integer qty;
    private Integer unitPrice;
    private String userId;

    private List<CartDto> cartList;
}
