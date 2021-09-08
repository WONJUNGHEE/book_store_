package com.example.userservice.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length= 50, unique = true)
    private String myid;
    @Column(nullable = false, unique = true)
    private String encryptedPwd;
    @Column(nullable = false, length= 50)
    private String name;
    @Column(nullable = false, length= 50, unique = true)
    private String email;
    @Column(nullable = false, length= 50, unique = true)
    private String phonenum;
    @Column(nullable = false, length= 100)
    private String address;
    @Column(nullable = false, unique = true)
    private String userId;
}