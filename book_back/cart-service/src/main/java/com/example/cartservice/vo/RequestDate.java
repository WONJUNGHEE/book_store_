package com.example.cartservice.vo;

import lombok.Data;

import java.time.LocalDate;

@Data
public class RequestDate {
    private LocalDate sDate;
    private LocalDate eDate;
}
