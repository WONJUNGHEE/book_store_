package com.example.cartservice.controller;

<<<<<<< HEAD
import com.example.cartservice.client.CatalogServiceClient;
import com.example.cartservice.dto.*;
import com.example.cartservice.jpa.CartEntity;
import com.example.cartservice.mq.KafkaProducer;
import com.example.cartservice.service.CartService;
import com.example.cartservice.vo.RequestCart;
import com.example.cartservice.vo.ResponseCatalog;
import com.example.cartservice.vo.ResponseCart;
=======
import com.example.cartservice.client.CatalogClient;
import com.example.cartservice.dto.CartDto;
import com.example.cartservice.jpa.CartEntity;
import com.example.cartservice.mq.CartProducer;
import com.example.cartservice.mq.KafkaProducer;
import com.example.cartservice.service.CartService;
import com.example.cartservice.vo.RequestCart;
import com.example.cartservice.vo.ResponseCart;
import com.example.cartservice.vo.ResponseCatalog;
>>>>>>> 610f58ab9daf17f514848a28b1b6c817dde405eb
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

<<<<<<< HEAD
import java.util.*;
=======
import java.util.ArrayList;
import java.util.List;
>>>>>>> 610f58ab9daf17f514848a28b1b6c817dde405eb

@RestController
@RequestMapping("/")
@Slf4j
public class CartController {
    Environment env;
    CartService cartService;
<<<<<<< HEAD
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

=======
    KafkaProducer kafkaProducer;
    CartProducer cartProducer;

    CatalogClient catalogClient;

    @Autowired
    public CartController(Environment env, CartService cartService,
                           KafkaProducer kafkaProducer,
                           CatalogClient catalogClient,
                           CartProducer cartProducer) {
        this.env = env;
        this.cartService = cartService;
        this.kafkaProducer = kafkaProducer;
        this.catalogClient = catalogClient;
        this.cartProducer = cartProducer;
>>>>>>> 610f58ab9daf17f514848a28b1b6c817dde405eb
    }

    @GetMapping("/health_check")
    public String status() {
<<<<<<< HEAD
        return String.format("It's Working in Order Service on PORT %s",
                env.getProperty("local.server.port"));
    }

    @PostMapping("/{userId}/carts")
    public ResponseEntity<ResponseCart> createOrder(@PathVariable("userId") String userId,
                                                    @RequestBody RequestCart cartDetails) {

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

    @GetMapping("/{userId}/carts")
    public ResponseEntity<List<ResponseCart>> getOrder(@PathVariable("userId") String userId) throws Exception {
        Iterable<CartEntity> cartList = cartService.getCartsByUserId(userId);
=======
        return String.format("It's Working in Cart Service on PORT %s",
                env.getProperty("local.server.port"));
    }

    @PostMapping("/{userId}/cart")
    public ResponseEntity<ResponseCart> createCart(@PathVariable("userId") String userId,
                                                    @RequestBody RequestCart cartDetails) {

        // check how much stock is left
        // order-service -> catalog-service
        // resttemplate or openfeign(o)
        boolean isAvailable = true;
        ResponseCatalog responseCatalog = catalogClient.getCatalog(cartDetails.getProductId());
        if (responseCatalog != null &&
                responseCatalog.getQty() - cartDetails.getQty() < 0)
            isAvailable = false;

        if (isAvailable) {
            ModelMapper mapper = new ModelMapper();
            mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

            CartDto cartDto = mapper.map(cartDetails, CartDto.class);
            cartDto.setUserId(userId);

            cartDto.setInstance_Id(env.getProperty("local.server.port"));

            CartDto createdCart = cartService.createCart(cartDto);
//            ResponseOrder responseOrder = mapper.map(createdOrder, ResponseOrder.class);

            /* send message to Kafka topic */
//            orderDto.setOrderId((UUID.randomUUID().toString()));
//            orderDto.setTotalPrice(orderDto.getQty() * orderDto.getUnitPrice());
            kafkaProducer.send("catalog", createdCart);
            ResponseCart responseCart = mapper.map(cartDto, ResponseCart.class);

            cartProducer.send("carts", cartDto);

            log.info("After added carts data");
            return ResponseEntity.status(HttpStatus.CREATED).body(responseCart);
        } else {
            log.info("After added carts data");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/{userId}/carts")
    public ResponseEntity<List<ResponseCart>> getCart(@PathVariable("userId") String userId) throws Exception {
        log.info("Before retrieve orders data ");
        Iterable<CartEntity> cartList = cartService.getCartByUserId(userId);
>>>>>>> 610f58ab9daf17f514848a28b1b6c817dde405eb

        List<ResponseCart> result = new ArrayList<>();
        cartList.forEach(v -> {
            result.add(new ModelMapper().map(v, ResponseCart.class));
        });
<<<<<<< HEAD
        log.info("After retrieve carts data");
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

=======

        log.info("Before retrieve orders data ");
        return ResponseEntity.status(HttpStatus.OK).body(result);
//        throw new Exception("Server not working!");
    }
>>>>>>> 610f58ab9daf17f514848a28b1b6c817dde405eb
}
