package com.app.ECommerce.service;

import com.app.ECommerce.dto.CartRequest;
import com.app.ECommerce.entity.CartItem;
import com.app.ECommerce.entity.Product;
import com.app.ECommerce.entity.User;
import com.app.ECommerce.repository.CartRepo;
import com.app.ECommerce.repository.ProductRepo;
import com.app.ECommerce.repository.UserRepo;
import jakarta.transaction.Transactional;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class CartServiceImpl implements CartService{
    private CartRepo cartRepo;
    private ProductRepo productRepo;
    private UserRepo userRepo;

    public CartServiceImpl(CartRepo cartRepo, ProductRepo productRepo, UserRepo userRepo) {
        this.cartRepo = cartRepo;
        this.productRepo = productRepo;
        this.userRepo = userRepo;
    }

    @Override
    public boolean addCart(Long userId, CartRequest request) {
      Optional<Product> product= productRepo.findById(request.getProductId());
       Optional<User> user= userRepo.findById(userId);
       if(product.isEmpty()){return false;}
        if(user.isEmpty()){return false;}
        Product existingProduct=product.get();
        if(existingProduct.getStockQuantity()<request.getQuantity()){return false;}
        User existingUser=user.get();
       CartItem existingCart= cartRepo.findByUserAndProduct(existingUser,existingProduct);
       if(existingCart!=null){
           existingCart.setQuantity(existingCart.getQuantity()+ request.getQuantity());
           existingCart.setPrice(product.get().getPrice().multiply(BigDecimal.valueOf(existingCart.getQuantity())));
           cartRepo.save(existingCart);
       }
       else {
           CartItem cartItem=new CartItem();
           cartItem.setUser(existingUser);
           cartItem.setProduct(existingProduct);
           cartItem.setQuantity(request.getQuantity());
           cartItem.setPrice(existingProduct.getPrice().multiply(BigDecimal.valueOf(request.getQuantity())));
           cartRepo.save(cartItem);

       }
        return true;
    }

    @Override
    public boolean deleteItemFromCart(Long userId, Long productId) {
        Optional<User> userOpt= userRepo.findById(userId);
        Optional<Product> productOpt= productRepo.findById(productId);
        if(userOpt.isEmpty() || productOpt.isEmpty()){return false;}
//       User user= userOpt.get();
//        Product product=productOpt.get();
//       CartItem cartItem= cartRepo.findByUserAndProduct(user,product);
//       if(cartItem==null){return false;}
//       cartRepo.delete(cartItem);
//        userOpt.flatMap(user->productOpt.map(product -> {
//            cartRepo.deleteByUserAndProduct(user,product);
//            return true;
//        }));
               if(userOpt.isPresent() &&productOpt.isPresent() ){
                   cartRepo.deleteByUserAndProduct(userOpt.get(),productOpt.get());
                   return true;
               }
       return false;
    }

    @Override
    public List<CartItem> getCartById(Long userId) {
        return userRepo.findById(userId).map(cartRepo::findByUser).orElseGet(List::of);
    }

    @Override
    @Transactional
    public void clearCart(Long userId) {
        userRepo.findById(userId).ifPresent(cartRepo::deleteByUser);
    }

}
