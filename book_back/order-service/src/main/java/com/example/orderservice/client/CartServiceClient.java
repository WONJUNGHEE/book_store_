
package com.example.orderservice.client;

import com.example.orderservice.vo.ResponseCart;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;


@FeignClient(name="cart-service")
public interface CartServiceClient {
    @GetMapping("/{userId}/carts")
    List<ResponseCart> getCart(@PathVariable String userId);
}



