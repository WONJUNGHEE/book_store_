package com.example.userservice.vo;
import lombok.Data;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class RequestUser {

    @NotNull(message = "Name cannot be null")
    @Size(min = 2, message = "Name not be less than two characters")
    private String userName;

    @NotNull(message = "Password cannot be null")
    @Size(min = 2, message = "Password not be less than two characters")
    private String pwd;

    @NotNull(message = "Your ID cannot be null")
    @Size(min = 2, message = "ID not be less than two characters")
    private String myId;

    @NotNull(message = "Phone number cannot be null")
    @Size(min = 2, max = 11, message = "Phone number is eleven characters")
    private String phoneNum;

    @NotNull(message = "Email cannot be null")
    @Size(min = 2, message = "Email not be less than two characters")
    @Email
    private String email;

    @NotNull(message = "address cannot be null")
    @Size(min = 2, max = 10, message = "Address not be less than two characters")
    private String address;
}