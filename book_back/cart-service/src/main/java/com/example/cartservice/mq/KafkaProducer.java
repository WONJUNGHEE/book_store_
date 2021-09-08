package com.example.cartservice.mq;

import com.example.cartservice.dto.CartDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class KafkaProducer {
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    public KafkaProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public CartDto send(String topic, CartDto cartDto) {
        ObjectMapper mapper = new ObjectMapper();
        String jsonInString  ="";
        try {
            jsonInString = mapper.writeValueAsString(cartDto);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        kafkaTemplate.send(topic, jsonInString);
        log.info("Requested cart was sent by Kafka Producer: " + jsonInString);

        return cartDto;
    }
}