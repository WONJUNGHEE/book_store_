package com.example.userservice.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
public class ResponseUser {

    private String myid;
    private String name;
    private String email;
    private String userId;

    private List<ResponseOrder> orders;
}