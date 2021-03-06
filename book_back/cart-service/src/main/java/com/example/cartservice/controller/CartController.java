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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.parser.Entity;
import java.util.*;

@Transactional
@RestController
@RequestMapping("/")
@Slf4j
public class CartController {
    Environment env;
    CartService cartService;
    KafkaProducer kafkaProducer;
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

                                                    @RequestBody RequestCart requestCart) {
        ResponseCatalog responseCatalog = catalogServiceClient.getCatalog(requestCart.getProductId());
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        CartDto cartDto = mapper.map(responseCatalog,CartDto.class);
        cartDto.setUserId(userId);
        cartDto.setQty(requestCart.getQty());
        cartDto.setTotalPrice(requestCart.getQty()*requestCart.getUnitPrice());
        CartDto existCartDto = cartService.getCartsByProductName(cartDto);

        if(existCartDto.getFind() == 1){
            existCartDto.setQty(cartDto.getQty()+existCartDto.getQty());
            existCartDto.setTotalPrice(cartDto.getQty()*cartDto.getUnitPrice()+existCartDto.getTotalPrice());
            cartService.updateCart(existCartDto);
            return ResponseEntity.status(HttpStatus.OK).body(null);
        }
        cartDto = cartService.createCart(cartDto);
        ResponseCart responseCart = mapper.map(cartDto,ResponseCart.class);
        return ResponseEntity.status(HttpStatus.OK).body(responseCart);


    }

//    }

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

    /* ???????????? ?????? ????????? */
    @DeleteMapping("/{userId}/carts")
    public void deleteCart(@PathVariable("userId") String userId) {
        cartService.deleteByUserId(userId);

    }

    /* ???????????? productName ????????? ????????? */
    @DeleteMapping("/{userId}/carts/{productName}")
    public void deleteEachCart(@PathVariable("userId") String userId, @PathVariable("productName") String productName) {
        cartService.deleteByProductName(productName);
    }
}

