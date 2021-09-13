package com.example.cartservice.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.time.LocalDate;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseCart {
    private String productId;
    private String productName;
    private Integer qty;
    private Integer unitPrice;
    private Integer totalPrice;
<<<<<<< HEAD
    private String category;
=======
<<<<<<< Updated upstream
    private String category;
=======
    private String src;
    private String detail;
>>>>>>> Stashed changes
>>>>>>> c8651ebec78bfe181ea87b434f295dc1f1bcea2b
}
