package com.app.ECommerce.service;

import com.app.ECommerce.dto.ProductRequest;
import com.app.ECommerce.dto.ProductResponse;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    ProductResponse createProduct(ProductRequest productRequest);

    ProductResponse updateProduct(Long id, ProductRequest productRequest);

    Optional<ProductResponse> getProductById(Long id);

    List<ProductResponse> getAllProducts();

    void deleteById(Long id);
}

