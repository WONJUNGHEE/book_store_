package com.example.cartservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;


@Data
@AllArgsConstructor
public class KafkaCartDto implements Serializable {
    private Schema schema;
    private Payload payload;

}

