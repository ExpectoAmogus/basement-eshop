package com.eshop.productservice.service.impl;

import com.eshop.productservice.models.dto.ProductResponse;
import com.eshop.productservice.models.mappers.ProductResponseMapper;
import com.eshop.productservice.service.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class ProductServiceImpl {
    private final ProductRepository productRepository;
    private final ProductResponseMapper productResponseMapper;

    public List<ProductResponse> getProducts(){
        return productRepository.findAll()
                .stream()
                .map(productResponseMapper)
                .toList();
    }

}
