package com.eshop.productqueryservice.facade.impl;

import com.eshop.productqueryservice.facade.ProductFacade;
import com.eshop.productqueryservice.models.dto.ProductResponse;
import com.eshop.productqueryservice.models.mappers.ProductResponseMapper;
import com.eshop.productqueryservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@Slf4j
@RequiredArgsConstructor
public class ProductFacadeImpl implements ProductFacade {
    private final ProductService productService;
    private final ProductResponseMapper productResponseMapper;

    @Override
    public List<ProductResponse> getProducts() {
        return productService.getProducts()
                .stream()
                .map(productResponseMapper)
                .toList();
    }

    @Override
    public ProductResponse findById(String id) {
        return productResponseMapper.apply(productService.findById(id));
    }
}
