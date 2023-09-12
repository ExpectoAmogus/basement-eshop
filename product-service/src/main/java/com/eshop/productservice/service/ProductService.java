package com.eshop.productservice.service;

import com.eshop.productservice.models.entity.Product;

import java.util.List;

public interface ProductService {
    Product createProduct(Product product);
    void updateProduct(Product product);
    List<Product> getProducts();
    Product findById(String id);
}
