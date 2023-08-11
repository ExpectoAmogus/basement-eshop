package com.eshop.productservice.service.impl;

import com.eshop.productservice.models.dto.ProductRequest;
import com.eshop.productservice.models.dto.ProductResponse;
import com.eshop.productservice.models.entity.Product;
import com.eshop.productservice.models.mappers.ProductCategoryMapper;
import com.eshop.productservice.models.mappers.ProductResponseMapper;
import com.eshop.productservice.repositories.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductCategoryService categoryService;
    private final ProductResponseMapper productResponseMapper;
    private final ProductCategoryMapper categoryMapper;

    public void createProduct(ProductRequest productRequest){
        if (!categoryService.getAllCategories()
                .stream()
                .map(categoryMapper)
                .toList()
                .contains(productRequest.category())){
            log.warn("Could not find category {}, creating new one...", productRequest.category().getName());
            categoryService.createCategory(productRequest.category().getName(), productRequest.category().getParent());
        }
        Product product = Product.builder()
                .name(productRequest.name())
                .code(productRequest.code())
                .description(productRequest.description())
                .spec(productRequest.spec())
                .category(productRequest.category())
                .price(productRequest.price())
                .build();

        productRepository.save(product);
        log.info("Product {} is created", product.getId());
    }

    public List<ProductResponse> getProducts(){
        return productRepository.findAll()
                .stream()
                .map(productResponseMapper)
                .toList();
    }

}
