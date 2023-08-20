package com.eshop.productservice.service;

import com.eshop.productservice.models.dto.ProductCreateResponse;
import com.eshop.productservice.models.dto.ProductRequest;
import com.eshop.productservice.models.dto.ProductResponse;

import java.util.List;

public interface ProductService {
    ProductCreateResponse createProduct(ProductRequest productRequest);
    void updateProduct(Long id, ProductRequest productRequest);
    List<ProductResponse> getProducts();
}
