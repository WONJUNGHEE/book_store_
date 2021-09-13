package com.example.catalogservice.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.Date;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseCatalog {
    private String productId;
    private String productName;
    private Integer qty;
    private Integer unitPrice;
    private String category;
<<<<<<< HEAD
    private String detail;
    private String src;
=======
    private String src;
    private String detail;
>>>>>>> c8651ebec78bfe181ea87b434f295dc1f1bcea2b
}
