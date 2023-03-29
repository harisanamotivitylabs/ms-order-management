package com.order.orderservice.controller;

import com.order.orderservice.dto.OrderRequest;
import com.order.orderservice.entity.Orders;
import com.order.orderservice.service.OrderService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;


@RestController
@RequestMapping("/order")
public class OrderController {


    @Autowired
    private OrderService orderService;


    @PostMapping("/placeOrder")
    @CircuitBreaker(name = "inventory",fallbackMethod = "fallbackMethod")
    @ResponseStatus(HttpStatus.OK)
    @TimeLimiter(name = "inventory")
    @Retry(name = "inventory")
    public CompletableFuture<String> placeOrder(@RequestBody OrderRequest orderRequest)
    {
       return   CompletableFuture.supplyAsync(()-> orderService.placeOrder(orderRequest));
    }

    public CompletableFuture<String> fallbackMethod(@RequestBody OrderRequest orderRequest,RuntimeException runtimeException)
    {
        return CompletableFuture.supplyAsync(()->"OOPs Something went wrong please try ,After Some Time!");
    }

}
