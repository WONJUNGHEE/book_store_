package com.example.orderservice.vo;

import com.example.orderservice.dto.CartDto;
import lombok.Data;

import java.util.List;

@Data
public class RequestOrder {
    private String productName;
    private Integer qty;
    private Integer unitPrice;
<<<<<<< Updated upstream
<<<<<<< Updated upstream
<<<<<<< Updated upstream
<<<<<<< Updated upstream
<<<<<<< HEAD
    private String productId;
=======
    private String userId;

    private List<CartDto> cartList;
>>>>>>> 308d72e012ab539beade09a33c416191ad380ff5
=======
    private String productId;
>>>>>>> Stashed changes
=======
    private String productId;
>>>>>>> Stashed changes
=======
    private String productId;
>>>>>>> Stashed changes
=======
    private String productId;
>>>>>>> Stashed changes
}
