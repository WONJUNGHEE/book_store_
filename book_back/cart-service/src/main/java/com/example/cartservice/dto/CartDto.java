package com.example.cartservice.dto;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

@Data
public class CartDto implements Serializable {
    private String productId;
<<<<<<< HEAD
    private String productName;
    private Integer qty;
    private Integer unitPrice;
    private Integer totalPrice;
    private String userId;
    private String cartId;
    private String category;
=======
    private Integer qty;
    private Integer unitPrice;
    private Integer totalPrice;
    private LocalDate createdat;
    private String cartId;
    private String userId;
    private String instance_Id;
>>>>>>> 610f58ab9daf17f514848a28b1b6c817dde405eb
}
