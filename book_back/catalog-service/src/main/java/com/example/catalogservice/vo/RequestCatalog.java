package com.example.catalogservice.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class RequestCatalog {
    @NotNull(message = "Please Enter productId")
    private String productId;
    @NotNull(message = "Please Enter productName")
    private String productName;
    @NotNull(message = "Please Enter qty")
    private Integer qty;
    @NotNull(message = "Please Enter unitPrice")
    private Integer unitPrice;
    @NotNull(message = "Please Enter category")
    private String category;
    @NotNull(message = "")
    private String src;
    @NotNull(message = "")
    private String detail;
}
