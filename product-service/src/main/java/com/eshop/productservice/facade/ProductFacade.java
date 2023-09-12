package com.eshop.productservice.facade;

import com.eshop.productservice.models.dto.ProductCreateResponse;
import com.eshop.productservice.models.dto.ProductRequest;
import com.eshop.productservice.models.dto.ProductResponse;
import com.eshop.productservice.models.dto.ProductToUpdateRequest;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

public interface ProductFacade {
    ProductCreateResponse createProduct(ProductRequest productRequest, HttpServletRequest request);
    void updateProduct(ProductToUpdateRequest updateRequest, HttpServletRequest request);
    List<ProductResponse> getProducts();
    ProductResponse findById(String id);
}
