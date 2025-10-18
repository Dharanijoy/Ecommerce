package com.app.ECommerce.service;

import com.app.ECommerce.dto.ProductRequest;
import com.app.ECommerce.dto.ProductResponse;
import com.app.ECommerce.entity.Product;
import com.app.ECommerce.mapper.ProductMappings;
import com.app.ECommerce.repository.ProductRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private ProductRepo repo;

    public ProductServiceImpl(ProductRepo repo) {
        this.repo = repo;
    }

    @Override
    public ProductResponse createProduct(ProductRequest productRequest) {
        Product product= ProductMappings.createProduct(productRequest);
        Product savedProduct= repo.save(product);
        return ProductMappings.mapToProdctResponse(savedProduct);
    }

    @Override
    public ProductResponse updateProduct(Long id, ProductRequest productRequest) {
        Product existingUser= repo.findById(id).orElse(null);
        if(existingUser==null){return null;}
        updateProduceFromRequest(existingUser,productRequest);
        Product existUser=  repo.save(existingUser);
        return ProductMappings.mapToProdctResponse(existUser);
    }

    @Override
    public Optional<ProductResponse> getProductById(Long id) {
        return repo.findById(id).map(ProductMappings::mapToProdctResponse);
    }

    @Override
    public List<ProductResponse> getAllProducts() {
        return repo.findAll().stream().map(ProductMappings::mapToProdctResponse).collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        repo.deleteById(id);
    }

    public void updateProduceFromRequest(Product product, ProductRequest productRequest){
        product.setName(productRequest.getName());
        product.setDescription(productRequest.getDescription());
        product.setPrice(productRequest.getPrice());
        product.setStockQuantity(productRequest.getStockQuantity());
        product.setCategory(productRequest.getCategory());
        product.setImageUrl(productRequest.getImageUrl());
    }

}
