package com.example.cartservice.client;

import com.example.cartservice.vo.ResponseCatalog;
import com.example.cartservice.vo.ResponseUser;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "user-service")
public interface UserServiceClient {
    @GetMapping("/users/{userId}")
    ResponseUser getUsers(@PathVariable String userId);
}
