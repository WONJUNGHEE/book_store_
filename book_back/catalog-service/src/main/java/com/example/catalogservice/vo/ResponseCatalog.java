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
    private String src;
    private String detail;
=======
    private String detail;
    private String src;
>>>>>>> 308d72e012ab539beade09a33c416191ad380ff5
}
