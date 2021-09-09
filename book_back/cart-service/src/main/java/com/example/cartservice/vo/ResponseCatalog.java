package com.example.cartservice.vo;

<<<<<<< HEAD

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;


=======
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.time.LocalDate;
import java.util.Date;
>>>>>>> 610f58ab9daf17f514848a28b1b6c817dde405eb

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseCatalog {
    private String productId;
    private String productName;
    private Integer qty;
<<<<<<< HEAD
    private Integer unitPrice;
    private String category;
}
=======

    private Integer unitPrice;
    private LocalDate createdAt;
    private String category;
}
>>>>>>> 610f58ab9daf17f514848a28b1b6c817dde405eb
