package com.eshop.productcommandservice.facade;

import com.eshop.productcommandservice.models.dto.ProductCreateResponse;
import com.eshop.productcommandservice.models.dto.ProductRequest;
import com.eshop.productcommandservice.models.dto.ProductToUpdateRequest;
import jakarta.servlet.http.HttpServletRequest;

public interface ProductFacade {
    ProductCreateResponse createProduct(ProductRequest productRequest, HttpServletRequest request);
    void updateProduct(ProductToUpdateRequest updateRequest, HttpServletRequest request);
    void deleteProduct(String code);
}
