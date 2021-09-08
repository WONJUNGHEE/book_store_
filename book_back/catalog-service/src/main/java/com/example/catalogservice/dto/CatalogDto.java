package com.example.catalogservice.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class CatalogDto implements Serializable {
    private String productId;
    private String productName;
    private String category;
    private Integer qty;
    private Integer unitPrice;
    private Integer totalPrice;

}
