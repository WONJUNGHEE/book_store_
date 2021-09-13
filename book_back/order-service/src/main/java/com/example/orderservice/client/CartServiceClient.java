package com.example.orderservice.client;

import com.example.orderservice.vo.ResponseCart;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;


@FeignClient(name="cart-service")
public interface CartServiceClient {
    @GetMapping("/{userId}/carts")
<<<<<<< HEAD
    List<ResponseCart> getCart(@PathVariable String userId);
=======
    ResponseCart getCart(@PathVariable("userId") String userId);
>>>>>>> c8651ebec78bfe181ea87b434f295dc1f1bcea2b
}


