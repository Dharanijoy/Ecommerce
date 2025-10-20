package com.app.ECommerce.repository;

import com.app.ECommerce.entity.CartItem;
import com.app.ECommerce.entity.Product;
import com.app.ECommerce.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface  CartRepo extends JpaRepository<CartItem,Long> {
    CartItem findByUserAndProduct(User existingUser, Product existingProduct);


    void deleteByUserAndProduct(User user, Product product);

    List<CartItem> findByUser(User user);

    void deleteByUser(User user);
}
