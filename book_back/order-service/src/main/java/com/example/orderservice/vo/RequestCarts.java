
package com.example.orderservice.vo;

import com.example.orderservice.dto.CartDto;
import lombok.Data;

import java.util.List;

@Data
public class RequestCarts {
    private String productId;
    private String productName;
    private Integer qty;
    private Integer unitPrice;
    private Integer totalPrice;
    private List<CartDto> cartList;

}

