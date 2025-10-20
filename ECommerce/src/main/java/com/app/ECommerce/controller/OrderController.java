package com.app.ECommerce.controller;

import com.app.ECommerce.dto.OrderResponse;
import com.app.ECommerce.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/order")
public class OrderController {
private OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }
    @PostMapping("/add/{userId}")
    public ResponseEntity<OrderResponse>createOrder(@PathVariable Long userId){
        return orderService.createOrder(userId).map(orderResponse ->
                 new ResponseEntity<>(orderResponse,HttpStatus.CREATED)).orElseGet(()->ResponseEntity.badRequest().build());


    }
}
