package com.app.ECommerce.service;

import com.app.ECommerce.dto.OrderItemsDTO;
import com.app.ECommerce.dto.OrderResponse;
import com.app.ECommerce.entity.CartItem;
import com.app.ECommerce.entity.OrderItem;
import com.app.ECommerce.entity.Orders;
import com.app.ECommerce.entity.User;
import com.app.ECommerce.enums.OrderStatus;
import com.app.ECommerce.repository.OrderRepo;
import com.app.ECommerce.repository.UserRepo;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {
private OrderRepo orderRepo;
private CartService cartService;
private UserRepo userRepo;

    public OrderServiceImpl(OrderRepo orderRepo, CartService cartService, UserRepo userRepo) {
        this.orderRepo = orderRepo;
        this.cartService = cartService;
        this.userRepo = userRepo;
    }

    @Override
    public Optional<OrderResponse> createOrder(Long userId) {
      List<CartItem> cartItems= cartService.getCartById(userId);
if(cartItems.isEmpty()){return Optional.empty();}
 Optional<User> userOpt=  userRepo.findById(userId);
        if(userOpt.isEmpty()){return Optional.empty();}
       User user= userOpt.get();
        BigDecimal totalPrice=  cartItems.stream().map(CartItem::getPrice).reduce(BigDecimal.ZERO,BigDecimal::add);
      //  BigDecimal.ZERO starting from zero.
        Orders order=new Orders();
        order.setUser(user);
        order.setStatus(OrderStatus.CONFIRMED);
        order.setTotalAmount(totalPrice);
        List<OrderItem>orderItems=cartItems.stream().map(item->new OrderItem(null,
                item.getQuantity(),
                item.getPrice(),
                item.getProduct(),
                order)).collect(Collectors.toList());
        order.setItems(orderItems);
       Orders savedOrder= orderRepo.save(order);

        cartService.clearCart(userId);
        return Optional.of(mapToOrderResponse(savedOrder));
    }

    private OrderResponse mapToOrderResponse(Orders savedOrder) {
        return new OrderResponse(savedOrder.getId(),
                savedOrder.getTotalAmount(),
                savedOrder.getStatus(),
                savedOrder.getItems().stream().map(orderItem ->new OrderItemsDTO(
                        orderItem.getId(),
                        orderItem.getProduct().getId(),
                        orderItem.getQuantity(),
                        orderItem.getPrice()
                )).toList(), savedOrder.getCreatedAt());
    }
}
