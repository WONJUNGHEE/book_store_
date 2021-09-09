package com.example.cartservice.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
<<<<<<< HEAD

import java.time.LocalDate;
=======
import org.apache.tomcat.jni.Local;

import java.time.LocalDate;
import java.util.Date;
>>>>>>> 610f58ab9daf17f514848a28b1b6c817dde405eb

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseCart {
    private String productId;
<<<<<<< HEAD
    private String productName;
    private Integer qty;
    private Integer unitPrice;
    private Integer totalPrice;
}
=======
    private Integer qty;
    private Integer unitPrice;
    private Integer totalPrice;
    private LocalDate createdAt;

    private String orderId;
    private String instance_Id;
}
>>>>>>> 610f58ab9daf17f514848a28b1b6c817dde405eb
