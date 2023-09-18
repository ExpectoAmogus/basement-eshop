package com.eshop.productqueryservice.service;

import com.eshop.productqueryservice.models.entity.Product;

import java.util.List;

public interface ProductService {
    List<Product> getProducts();
    Product findById(String id);
    Product findByCode(String code);
}
