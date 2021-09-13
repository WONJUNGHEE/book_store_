package com.example.cartservice.jpa;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name="carts")
public class CartEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //
    @Column(nullable = false, unique = true)
    private String cartId;
    @Column(nullable = false)
    private String productId; //
    @Column(nullable = false)
    private String productName; //
    @Column(nullable = false)
    private Integer qty; //
    @Column(nullable = false)
    private Integer unitPrice; //
    @Column(nullable = false)
    private Integer totalPrice; //
    @Column(nullable = false)
    private String userId; //
    @Column(nullable = false)
    private String category; //
    @Column
    private String detail;

}
