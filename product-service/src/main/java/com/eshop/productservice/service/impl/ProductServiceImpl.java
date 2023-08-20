package com.eshop.productservice.service.impl;

import com.eshop.productservice.models.dto.*;
import com.eshop.productservice.models.entity.Product;
import com.eshop.productservice.models.entity.ProductCategory;
import com.eshop.productservice.models.entity.ProductSpec;
import com.eshop.productservice.models.mappers.ProductCategoryMapper;
import com.eshop.productservice.models.mappers.ProductResponseMapper;
import com.eshop.productservice.models.mappers.ProductSpecMapper;
import com.eshop.productservice.repositories.ProductRepository;
import com.eshop.productservice.service.ProductCategoryService;
import com.eshop.productservice.service.ProductService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductCategoryService categoryService;
    private final ProductResponseMapper productResponseMapper;
    private final ProductCategoryMapper productCategoryMapper;
    private final ProductSpecMapper productSpecMapper;
    private final WebClient.Builder webClientBuilder;

    @Override
    public ProductCreateResponse createProduct(ProductRequest productRequest) {
        ProductCategory category;
        ProductSpec productSpec;
        if (!categoryExists(productRequest)) {
            log.warn("Could not find category {}, creating new one...", productRequest.category().name());
            category = productCategoryMapper.apply(categoryService.createCategory(productRequest.category()));
        } else {
            category = productCategoryMapper.apply(
                    categoryService.findByNameAndParentId(
                            productRequest.category().name(),
                            productRequest.category().parentId()
                    )
            );
        }

        ProductSpecDto specDto = productRequest.spec();
        productSpec = productSpecMapper.apply(specDto);

        Product product = Product.builder()
                .name(productRequest.name())
                .code(productRequest.code())
                .description(productRequest.description())
                .spec(productSpec)
                .category(category)
                .price(productRequest.price())
                .build();
        productRepository.save(product);

        webClientBuilder.build().post()
                .uri("http://inventory-service/api/inventory/add",
                        uriBuilder -> uriBuilder
                                .queryParam("code", productRequest.code())
                                .queryParam("quantity", productRequest.quantity())
                                .build())
                .retrieve()
                .toBodilessEntity()
                .block();

        log.info("Product {} is created", product.getId());
        return new ProductCreateResponse(product.getId(), product.getCode());
    }

    @Override
    public List<ProductResponse> getProducts() {
        return productRepository.findAll()
                .stream()
                .map(productResponseMapper)
                .toList();
    }

    private boolean categoryExists(ProductRequest productRequest) {
        return categoryService.getAllCategories()
                .stream()
                .map(ProductCategoryDto::name)
                .toList()
                .contains(productRequest.category().name());
    }

}
