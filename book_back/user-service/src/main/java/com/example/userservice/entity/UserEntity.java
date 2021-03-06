package com.example.userservice.entity;

import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@Table(name="users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String userId;
    @Column(nullable = false, length= 50, unique = true)
    private String myId;
    @Column(nullable = false, unique = true)
    private String encryptedPwd;
    @Column(nullable = false, length= 50)
    private String userName;
    @Column(nullable = false, length= 50)
    private String email;
    @Column(nullable = false, length= 50)
    private String phoneNum;
    @Column(nullable = false, length= 100)
    private String address;
    @Column(nullable = false,updatable = false, insertable = false)
    @ColumnDefault(value = "CURRENT_TIMESTAMP")
    private LocalDate createdAt;

}