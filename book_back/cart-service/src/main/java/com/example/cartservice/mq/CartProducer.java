package com.example.cartservice.mq;

import com.example.cartservice.dto.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
@Slf4j
public class CartProducer {
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    public CartProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    List<Field> fields = Arrays.asList(new Field("string", true, "order_id"),
            new Field("string", true, "user_id"),
            new Field("string", true, "product_id"),
            new Field("int32", true, "qty"),
            new Field("int32", true, "unit_price"),
            new Field("int32", true, "total_price"),
            new Field("int32", true, "instance_Id"));
    Schema schema = Schema.builder()
            .type("struct")
            .fields(fields)
            .optional(false)
            .name("orders")
            .build();

    public CartDto send(String topic, CartDto cartDto) {
        Payload payload = Payload.builder()
                .cart_id(cartDto.getCartId())
                .user_id(cartDto.getUserId())
                .product_id(cartDto.getProductId())
                .qty(cartDto.getQty())
                .unit_price(cartDto.getUnitPrice())
                .total_price(cartDto.getTotalPrice())
                .instance_id(cartDto.getInstance_Id())
                .build();

        KafkaCartDto kafkaCartDto = new KafkaCartDto(schema, payload);

        ObjectMapper mapper = new ObjectMapper();
        String jsonInString  ="";
        try {
            jsonInString = mapper.writeValueAsString(kafkaCartDto);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        kafkaTemplate.send(topic, jsonInString);
        log.info("Cart Producer send data from the cart-service: " + kafkaCartDto);

        return cartDto;
    }
}