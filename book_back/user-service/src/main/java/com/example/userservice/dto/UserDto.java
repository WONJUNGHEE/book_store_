package com.example.userservice.dto;

import com.example.userservice.vo.ResponseOrder;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class UserDto {

    private String myid;
    private String pwd;
    private String name;
    private String email;
    private String phonenum;
    private String address;


    private String userId;
    private Date createdAt;

    private String decryptedPwd;
    private String encryptedPwd;

    private List<ResponseOrder> orders;
}
