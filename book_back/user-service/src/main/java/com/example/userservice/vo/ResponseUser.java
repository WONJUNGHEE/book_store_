package com.example.userservice.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.List;

@Data
public class ResponseUser {

    private String myId;
    private String userName;
    private String email;
    private String userId;
    private String address;

    private List<ResponseOrder> orders;
}