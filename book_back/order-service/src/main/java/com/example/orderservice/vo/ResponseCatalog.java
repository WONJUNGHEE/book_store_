package com.example.orderservice.vo;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;



@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseCatalog {
    private String productId;
    private String productName;
    private Integer qty;
    private Integer unitPrice;
    private String category;
}
