package com.example.orderservice.vo;

import com.example.orderservice.dto.CartDto;
import lombok.Data;

import java.util.List;

@Data
public class RequestOrder {
    private String productName;
    private Integer qty;
    private Integer unitPrice;
<<<<<<< HEAD
    private String userId;

    private List<CartDto> cartList;
=======
    private String productId;
>>>>>>> c8651ebec78bfe181ea87b434f295dc1f1bcea2b
}
