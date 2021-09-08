package com.example.userservice.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="users")
public class UserEntity {
    @Id
    @Column(nullable = false)
    private String userId;

    @Column(nullable = false, length= 50, unique = true)
    private String myid;
    @Column(nullable = false, unique = true)
    private String encryptedPwd;
    @Column(nullable = false, length= 50)
    private String name;
    @Column(nullable = false, length= 50)
    private String email;
    @Column(nullable = false, length= 50)
    private String phonenum;
    @Column(nullable = false, length= 100)
    private String address;

}