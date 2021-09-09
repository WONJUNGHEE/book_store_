package com.example.cartservice.controller;

import com.example.cartservice.client.CatalogServiceClient;
import com.example.cartservice.dto.*;
import com.example.cartservice.jpa.CartEntity;
import com.example.cartservice.mq.KafkaProducer;
import com.example.cartservice.service.CartService;
import com.example.cartservice.vo.RequestCart;
import com.example.cartservice.vo.ResponseCatalog;
import com.example.cartservice.vo.ResponseCart;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/")
@Slf4j
public class CartController {
    Environment env;
    CartService cartService;
    KafkaProducer  kafkaProducer;
    CatalogServiceClient catalogServiceClient;

    @Autowired
    public CartController(Environment env, CartService cartService, KafkaProducer kafkaProducer,
                          CatalogServiceClient catalogServiceClient
                          ) {
        this.env = env;
        this.cartService = cartService;
        this.kafkaProducer = kafkaProducer;
        this.catalogServiceClient = catalogServiceClient;

    }

    @GetMapping("/health_check")
    public String status() {
        return String.format("It's Working in Order Service on PORT %s",
                env.getProperty("local.server.port"));
    }

    @PostMapping("/{userId}/carts")
    public ResponseEntity<ResponseCart> createOrder(@PathVariable("userId") String userId,
                                                    @RequestBody RequestCart cartDetails) {

        if(cartService.getCartsByProductName(userId,cartDetails.getProductName()) != null) {
            ModelMapper mapper = new ModelMapper();
            mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

            CartDto cartDto = cartService.getCartsByProductName(userId,cartDetails.getProductName());
            cartDto.setQty(cartDto.getQty()+cartDetails.getQty());
            cartDto.setTotalPrice(cartDto.getQty()*cartDto.getUnitPrice());
            CartDto updatedCart = cartService.updateCart(cartDto);
            ResponseCart responseCart = mapper.map(updatedCart,ResponseCart.class);

            return ResponseEntity.status(HttpStatus.CREATED).body(responseCart);
        } else {
            ResponseCatalog responseCatalog = catalogServiceClient.getCatalog(cartDetails.getProductId());
            ModelMapper mapper = new ModelMapper();
            mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

            CartDto cartDto = mapper.map(cartDetails, CartDto.class);
            cartDto.setUserId(userId);
            cartDto.setCategory(responseCatalog.getCategory());
            CartDto createdOrder = cartService.createCart(cartDto);

            kafkaProducer.send("orders", cartDto);
            ResponseCart responseCart = mapper.map(createdOrder, ResponseCart.class);

            log.info("After added carts data");
            return ResponseEntity.status(HttpStatus.CREATED).body(responseCart);
        }

    }

    @GetMapping("/{userId}/carts")
    public ResponseEntity<List<ResponseCart>> getOrder(@PathVariable("userId") String userId) throws Exception {
        Iterable<CartEntity> cartList = cartService.getCartsByUserId(userId);

        List<ResponseCart> result = new ArrayList<>();
        cartList.forEach(v -> {
            result.add(new ModelMapper().map(v, ResponseCart.class));
        });
        log.info("After retrieve carts data");
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}
