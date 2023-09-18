package com.eshop.productqueryservice.facade;

import com.eshop.productqueryservice.models.dto.ProductResponse;

import java.util.List;

public interface ProductFacade {

    List<ProductResponse> getProducts();
    ProductResponse findById(String id);

}
