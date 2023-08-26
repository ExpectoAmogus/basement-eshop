package com.eshop.productservice.facade;

import com.eshop.productservice.models.dto.ProductCreateResponse;
import com.eshop.productservice.models.dto.ProductRequest;
import com.eshop.productservice.models.dto.ProductResponse;

import java.util.List;

public interface ProductFacade {
    ProductCreateResponse createProduct(ProductRequest productRequest);
    void updateProduct(Long id, ProductRequest productRequest);
    List<ProductResponse> getProducts();
    ProductResponse findById(Long id);
}
