package com.example.userservice.dto;

import com.example.userservice.vo.ResponseOrder;
import lombok.Data;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Data
public class UserDto {

    private String myId;
    private String pwd;
    private String userName;
    private String email;
    private String phoneNum;
    private String address;
    private String userId;
    private LocalDate createdAt;

    private String decryptedPwd;
    private String encryptedPwd;

    private List<ResponseOrder> orders;
}
