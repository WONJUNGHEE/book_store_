//package com.example.cartservice.mq;
//
//
//import com.example.cartservice.dto.*;
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.kafka.core.KafkaTemplate;
//import org.springframework.stereotype.Service;
//
//import java.util.Arrays;
//import java.util.List;
//
//@Service
//@Slf4j
//@RequiredArgsConstructor
//public class OrderProducer {
//    private final KafkaTemplate<String,String> kafkaTemplate;
//
//    List<Field> fields = Arrays.asList(
//        new Field("string", true, "order_id"),
//        new Field("string", true, "user_id"),
//        new Field("string", true, "product_id"),
//        new Field("int32", true, "qty"),
//        new Field("int32", true, "unit_price"),
//        new Field("int32", true, "total_price"),
//        new Field("int32", true, "instance_id")
//    );
//
//    Schema schema = Schema.builder()
//            .type("struct")
//            .fields(fields)
//            .optional(false)
//            .name("orders")
//            .build();
//
//    public CartDto send (String topic, CartDto cartDto){
//        Payload payload = Payload.builder()
//                .order_id(cartDto.getOrderId())
//                .user_id(cartDto.getUserId())
//                .product_id(cartDto.getProductId())
//                .qty(cartDto.getQty())
//                .unit_price(cartDto.getUnitPrice())
//                .total_price(cartDto.getTotalPrice())
//                .build();
//        KafkaOrderDto kafkaOrderDto = new KafkaOrderDto(schema, payload);
//        ObjectMapper mapper = new ObjectMapper();
//        String jsonInString = "";
//        try {
//            jsonInString = mapper.writeValueAsString(kafkaOrderDto);
//            log.info(jsonInString);
//        } catch (
//                JsonProcessingException e) {
//            e.printStackTrace();
//        }
//        kafkaTemplate.send(topic, jsonInString);
//        log.info("Order Producer send data from the order-sevice" + kafkaOrderDto);
//
//        return cartDto;
//    }
//}
