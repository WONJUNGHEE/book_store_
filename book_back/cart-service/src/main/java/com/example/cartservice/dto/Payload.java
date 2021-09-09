package com.example.cartservice.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Payload {
<<<<<<< HEAD
    private String order_id;
    private String user_id;
    private String product_id;
    private int qty;
    private int unit_price;
    private int total_price;
    private String instance_id;
}
=======
    private String cart_id;
    private String user_id;
    private String product_id;
    private String instance_id;
    private int qty;
    private int total_price;
    private int unit_price;

}
>>>>>>> 610f58ab9daf17f514848a28b1b6c817dde405eb
