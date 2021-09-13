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
    @Column(nullable = false)
    private String userId;
<<<<<<< Updated upstream
<<<<<<< Updated upstream

    @Column(nullable = false, length= 50, unique = true)
=======
    @Column(nullable = false, unique = true)
>>>>>>> Stashed changes
    private String myId;
    @Column(nullable = false, unique = true)
    private String encryptedPwd;
    @Column(nullable = false)
    private String userName;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String phoneNum;
<<<<<<< Updated upstream
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
=======
    @Column(nullable = false)
>>>>>>> Stashed changes
    private String address;
    @Column(nullable = false,updatable = false, insertable = false)
    @ColumnDefault(value = "CURRENT_TIMESTAMP")
    private LocalDate createdAt;

}