package com.example.orderservice.client;

import com.example.orderservice.vo.ResponseCart;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;


@FeignClient(name="cart-service")
public interface CartServiceClient {
    @GetMapping("/{userId}/carts")
<<<<<<< Updated upstream
<<<<<<< Updated upstream
<<<<<<< HEAD
    ResponseCart getCart(@PathVariable("userId") String userId);
=======
    List<ResponseCart> getCart(@PathVariable String userId);
>>>>>>> 308d72e012ab539beade09a33c416191ad380ff5
=======
    ResponseCart getCart(@PathVariable("userId") String userId);
>>>>>>> Stashed changes
=======
    ResponseCart getCart(@PathVariable("userId") String userId);
>>>>>>> Stashed changes
}


