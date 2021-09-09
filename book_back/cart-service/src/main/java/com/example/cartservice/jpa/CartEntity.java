package com.example.cartservice.jpa;

import lombok.Data;
<<<<<<< HEAD

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
=======
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

@Data
@Entity
@Table(name="cart")
public class CartEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 120, unique = true)
    private String productId;
    @Column(nullable = false)
    private Integer qty;
    @Column(nullable = false)
    private Integer unitPrice;
    @Column(nullable = false)
    private Integer totalPrice;

    @Column(nullable = false)
    private String userId;
    @Column(nullable = false, unique = true)
    private String cartId;
    @Column(nullable = false)
    private String instance_Id;

    @Column(nullable = false, updatable = false, insertable = false)
    @ColumnDefault(value = "CURRENT_TIMESTAMP")
    private LocalDate createdAt;
}
>>>>>>> 610f58ab9daf17f514848a28b1b6c817dde405eb
