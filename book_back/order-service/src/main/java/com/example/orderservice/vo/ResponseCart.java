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
    private String category;

    private List<ResponseCart> responseCartList;
}
