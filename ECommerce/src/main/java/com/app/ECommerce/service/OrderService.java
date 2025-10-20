package com.app.ECommerce.service;

import com.app.ECommerce.dto.OrderResponse;

import java.util.Optional;

public interface OrderService {
    Optional<OrderResponse> createOrder(Long userId);
}
