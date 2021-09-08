package com.example.userservice.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class RequestLogin {
    @NotNull(message = "Myid cannot be null")
    @Size(min = 2, message = "Myid not be less than two characters")
    private String myid;

    @NotNull(message = "Password cannot be null")
    @Size(min = 8, message = "Password must be equals or grater than 8 characters")
    private String pwd;
}