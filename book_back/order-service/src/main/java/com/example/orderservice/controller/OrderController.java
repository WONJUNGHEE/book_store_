
package com.example.orderservice.controller;

import com.example.orderservice.client.CartServiceClient;
import com.example.orderservice.client.CatalogServiceClient;
import com.example.orderservice.dto.*;
import com.example.orderservice.jpa.OrderEntity;
import com.example.orderservice.mq.KafkaProducer;
import com.example.orderservice.mq.OrderProducer;
import com.example.orderservice.service.OrderService;
import com.example.orderservice.vo.*;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.*;

@RestController
@RequestMapping("/")
@Slf4j
public class OrderController {
    Environment env;
    OrderService orderService;
    KafkaProducer  kafkaProducer;
    CatalogServiceClient catalogServiceClient;
    CartServiceClient cartServiceClient;
    OrderProducer orderProducer;

    @Autowired
    public OrderController(Environment env, OrderService orderService,KafkaProducer kafkaProducer,
                           CatalogServiceClient catalogServiceClient, CartServiceClient cartServiceClient,
                           OrderProducer orderProducer) {
        this.env = env;
        this.orderService = orderService;
        this.kafkaProducer = kafkaProducer;
        this.catalogServiceClient = catalogServiceClient;
        this.cartServiceClient = cartServiceClient;
        this.orderProducer = orderProducer;
    }

    @GetMapping("/health_check")
    public String status() {
        return String.format("It's Working in Order Service on PORT %s",
                env.getProperty("local.server.port"));
    }

    @PostMapping("/{userId}/orders")
    public ResponseEntity<ResponseOrder> createOrder(@PathVariable("userId") String userId,
                                                     @RequestBody RequestOrder orderDetails) {
        log.info("Before add orders data");
        //order-service -> catalog-service
        //rest template or openfeign
        boolean isAvailable = true;
        ResponseCatalog responseCatalog = catalogServiceClient.getCatalog(orderDetails.getProductId());
        System.out.println(responseCatalog);
        System.out.println(orderDetails);
        if(responseCatalog != null &&
                responseCatalog.getQty()-orderDetails.getQty() < 0)
            isAvailable = false;

        if (isAvailable) {
            ModelMapper mapper = new ModelMapper();
            mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

            OrderDto orderDto = mapper.map(orderDetails, OrderDto.class);
            orderDto.setUserId(userId);
            orderDto.setCategory(responseCatalog.getCategory());
            orderDto.setProductId(responseCatalog.getProductId());
            orderDto.setProductName(responseCatalog.getProductName());
            OrderDto createdOrder = orderService.createOrder(orderDto);

            kafkaProducer.send("orders", orderDto);
            ResponseOrder responseOrder = mapper.map(createdOrder, ResponseOrder.class);


            log.info("After added orders data");
            return ResponseEntity.status(HttpStatus.CREATED).body(responseOrder);
        } else {
            log.info("After added orders data");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

    }
    @PostMapping("/{userId}/carts/orders")
    public ResponseEntity<List<ResponseOrder>> createOrdersByCart(@PathVariable("userId") String userId) {
        boolean isAvailable = true;
        List<ResponseCart> responseCart = cartServiceClient.getCart(userId);
        List<ResponseOrder> responseOrder = new ArrayList<>();
        /* responseCart를 list로 받는다. */
        for (ResponseCart v : responseCart) {
            ResponseCatalog responseCatalog = catalogServiceClient.getCatalog(v.getProductId());
            if (v != null &&
                    responseCatalog.getQty() - v.getQty()  < 0) {
                isAvailable = false;
            }
            if (isAvailable) {
                ModelMapper mapper = new ModelMapper();
                mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
                /* 매핑 강도 설정 */
                OrderDto order = mapper.map(v, OrderDto.class);
                order.setUserId(userId);
                order.setCategory(v.getCategory()); /* orderDto에 responseCart 아이템 v 의 category를 넣어준다 */
                order.setProductId(v.getProductId());
                order.setProductName(v.getProductName());

                OrderDto createdOrdersByCart = orderService.createOrderByCart(order);

                responseOrder.add(mapper.map(createdOrdersByCart, ResponseOrder.class));

                kafkaProducer.send("orders",createdOrdersByCart);
                System.out.println(responseOrder);
            }
        }
            return ResponseEntity.status(HttpStatus.CREATED).body(responseOrder);
    }

        @GetMapping("/{userId}/orders")
        public ResponseEntity<List<ResponseOrder>> getOrder(@PathVariable("userId") String userId) throws Exception {
            Iterable<OrderEntity> orderList = orderService.getOrdersByUserId(userId);

            List<ResponseOrder> result = new ArrayList<>();
            orderList.forEach(v -> {
                result.add(new ModelMapper().map(v, ResponseOrder.class));
            });
//        Random rnd = new Random(System.currentTimeMillis());
//        int time = rnd.nextInt(3);
//        if (time % 2 == 0) {
//            try {
//                Thread.sleep(1000);
//                throw new Exception();
//            } catch (InterruptedException ex) {
//                log.warn(ex.getMessage());
//            }
//        }
            log.info("After retrieve orders data");
            return ResponseEntity.status(HttpStatus.OK).body(result);
        }

        @RequestMapping("{userId}/date")  // 쿼리스트링 userId?sDate=2012-12-12 & eDate=2020-12-12 @ReuqestParam
        public ResponseEntity<List<ResponseOrder>> getOrdersByDate(@PathVariable("userId") String userId,
                @RequestParam("sDate") String s_Date,
                @RequestParam("eDate") String e_Date ) {

            ModelMapper mapper = new ModelMapper();
            mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
            LocalDate sDate = mapper.map(s_Date,LocalDate.class);
            LocalDate eDate = mapper.map(e_Date,LocalDate.class);
            Iterable<OrderEntity> orderList = orderService.getOrdersByUserIdAndOrderedAt(userId,sDate,eDate);
            List<ResponseOrder> result = new ArrayList<>();
            orderList.forEach(v -> {
                result.add(new ModelMapper().map(v, ResponseOrder.class));
            });
            return ResponseEntity.status(HttpStatus.OK).body(result);
        }

    }