package com.app.ECommerce.controller;

import com.app.ECommerce.dto.CartRequest;
import com.app.ECommerce.entity.CartItem;
import com.app.ECommerce.service.CartService;
import jakarta.annotation.Resource;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/cart")
@Transactional
public class CartController {
    private CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }
    @PostMapping("add/{userId}")
    public ResponseEntity<String> addCart(@PathVariable Long userId, @RequestBody CartRequest request){
        if(!cartService.addCart(userId,request)){
            return ResponseEntity.badRequest().body("Product or User is not found (or) Product quantity is Less");
        }
        return ResponseEntity.ok("Cart added successfully");
    }
    @DeleteMapping("/{userId}/{productId}")
    public ResponseEntity<String> deleteItemFromCart(@PathVariable Long userId,@PathVariable Long productId)
    {
       boolean deleted= cartService.deleteItemFromCart(userId,productId);
       if(!deleted){return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cart item not found for given user or product.");}
       else {return ResponseEntity.ok("CartItem deleted successfully.");}
    }
    @GetMapping("/{userId}")
    public ResponseEntity<List<CartItem>>getCartById(@PathVariable Long userId)
    {
        List<CartItem> carts= cartService.getCartById(userId);
        return new ResponseEntity<>(carts,HttpStatus.OK);
    }
}
