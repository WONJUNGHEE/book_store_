package com.example.orderservice.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseCart {

    private String productId;
    private String productName;
    private Integer qty;
    private Integer unitPrice;
<<<<<<< Updated upstream
=======
    private Integer totalPrice;
>>>>>>> Stashed changes
    private String category;
}
