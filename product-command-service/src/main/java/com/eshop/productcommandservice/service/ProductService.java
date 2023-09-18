package com.eshop.productcommandservice.service;

import com.eshop.productcommandservice.models.entity.Product;

import java.util.List;

public interface ProductService {
    Product createProduct(Product product);
    void updateProduct(Product product);
    void deleteProduct(Product product);
    List<Product> getProducts();
    Product findById(String id);
    Product findByCode(String code);
}
