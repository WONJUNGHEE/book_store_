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
<<<<<<< Updated upstream

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
=======
    @Column(nullable = false, unique = true)
    private String myId;
    @Column(nullable = false, unique = true)
    private String encryptedPwd;
    @Column(nullable = false)
    private String userName;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String phoneNum;
    @Column(nullable = false)
>>>>>>> Stashed changes
    private String address;

}