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
<<<<<<< HEAD
    private List<CartDto> cartList;
=======

//    private List<UserId> userId;
//
//    public class UserId {
//        private String productId;
//        private String productName;
//        private Integer qty;
//        private Integer unitPrice;
//        private Integer totalPrice;
//
//    }

    private List<CartDto> cartList;

>>>>>>> 538ce06c378ec7b83669b1964f36a3afc1bf4aa1
}
