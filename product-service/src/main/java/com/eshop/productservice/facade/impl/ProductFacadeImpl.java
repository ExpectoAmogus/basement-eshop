package com.eshop.productservice.facade.impl;

import com.eshop.productservice.facade.ProductFacade;
import com.eshop.productservice.models.dto.*;
import com.eshop.productservice.models.entity.Product;
import com.eshop.productservice.models.mappers.ProductCategoryMapper;
import com.eshop.productservice.models.mappers.ProductResponseMapper;
import com.eshop.productservice.models.mappers.ProductSpecMapper;
import com.eshop.productservice.service.ProductService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductFacadeImpl implements ProductFacade {
    private final ProductService productService;
    private final ProductResponseMapper productResponseMapper;
    private final ProductSpecMapper productSpecMapper;
    private final ProductCategoryMapper productCategoryMapper;
    private final KafkaTemplate<String, InventoryRequest> kafkaTemplate;

    @Override
    public ProductCreateResponse createProduct(ProductRequest productRequest, HttpServletRequest request) {
        Product product = Product.builder()
                .name(productRequest.name())
                .code(productRequest.code())
                .description(productRequest.description())
                .category(productCategoryMapper.apply(productRequest.category()))
                .spec(productSpecMapper.apply(productRequest.spec()))
                .price(productRequest.price())
                .build();
        Product createdProduct = productService.createProduct(product);

        kafkaTemplate.send("inventory-topic", new InventoryRequest(
                productRequest.code(),
                productRequest.quantity()
        ));

        return new ProductCreateResponse(createdProduct.getId(), createdProduct.getCode());
    }

    @Override
    public void updateProduct(ProductToUpdateRequest updateRequest, HttpServletRequest request) {
        try {
            Product existingProduct = productService.findById(updateRequest.id());

            Product product = Product.builder()
                    .id(existingProduct.getId())
                    .code(updateRequest.code())
                    .name(updateRequest.name())
                    .description(updateRequest.description())
                    .spec(productSpecMapper.apply(updateRequest.spec()))
                    .category(productCategoryMapper.apply(updateRequest.category()))
                    .price(updateRequest.price())
                    .build();

            productService.updateProduct(product);

            kafkaTemplate.send("inventory-topic", new InventoryRequest(
                    updateRequest.code(),
                    updateRequest.quantity()
            ));

        } catch (EntityNotFoundException e) {
            log.error("Product does not exist!");
        }
    }

    @Override
    public List<ProductResponse> getProducts() {
        return productService.getProducts()
                .stream()
                .map(productResponseMapper)
                .toList();
    }

    @Override
    public ProductResponse findById(Long id) {
        return productResponseMapper.apply(productService.findById(id));
    }
}
