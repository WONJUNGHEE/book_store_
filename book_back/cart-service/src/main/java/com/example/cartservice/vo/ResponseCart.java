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
<<<<<<< Updated upstream
    private String category;
=======
    private String src;
    private String detail;
>>>>>>> Stashed changes
=======
    private String category;
>>>>>>> 308d72e012ab539beade09a33c416191ad380ff5
}
