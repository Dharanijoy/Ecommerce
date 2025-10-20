package com.app.ECommerce.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Data
@AllArgsConstructor
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer quantity;
    private BigDecimal price;
    @ManyToOne
    @JoinColumn(name ="product_id",nullable = false)
    private Product product;
    @ManyToOne
    @JoinColumn(name ="order_id",nullable = false)
    private Orders order;
}
