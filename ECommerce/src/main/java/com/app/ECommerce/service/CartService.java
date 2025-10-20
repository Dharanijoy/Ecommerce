package com.app.ECommerce.service;

import com.app.ECommerce.dto.CartRequest;
import com.app.ECommerce.entity.CartItem;

import java.util.List;

public interface CartService {
    boolean addCart(Long userId, CartRequest request);

    boolean deleteItemFromCart(Long userId, Long productId);

    List<CartItem> getCartById(Long userId);

    void clearCart(Long userId);
}
