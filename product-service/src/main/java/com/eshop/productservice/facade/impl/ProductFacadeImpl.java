package com.eshop.productservice.facade.impl;

import com.eshop.productservice.facade.ProductFacade;
import com.eshop.productservice.models.dto.ProductCreateResponse;
import com.eshop.productservice.models.dto.ProductRequest;
import com.eshop.productservice.models.dto.ProductResponse;
import com.eshop.productservice.models.entity.Product;
import com.eshop.productservice.models.mappers.ProductCategoryMapper;
import com.eshop.productservice.models.mappers.ProductResponseMapper;
import com.eshop.productservice.models.mappers.ProductSpecMapper;
import com.eshop.productservice.service.ProductService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
    private final WebClient.Builder webClientBuilder;

    @Override
    public ProductCreateResponse createProduct(ProductRequest productRequest) {
        Product product = Product.builder()
                .name(productRequest.name())
                .code(productRequest.code())
                .description(productRequest.description())
                .category(productCategoryMapper.apply(productRequest.category()))
                .spec(productSpecMapper.apply(productRequest.spec()))
                .price(productRequest.price())
                .build();
        productService.createProduct(product);

        updateInventory(
                webClientBuilder.build().post(),
                "http://inventory-service/api/inventory/add",
                productRequest.code(),
                productRequest.quantity()
        );

        return new ProductCreateResponse(product.getId(), product.getCode());
    }

    @Override
    public void updateProduct(Long id, ProductRequest productRequest) {
        try {
            Product existingProduct = productService.findById(id);

            Product product = Product.builder()
                    .id(existingProduct.getId())
                    .code(productRequest.code())
                    .name(productRequest.name())
                    .description(productRequest.description())
                    .spec(productSpecMapper.apply(productRequest.spec()))
                    .category(productCategoryMapper.apply(productRequest.category()))
                    .price(productRequest.price())
                    .build();

            productService.updateProduct(product);

            updateInventory(
                    webClientBuilder.build().put(),
                    "http://inventory-service/api/inventory/update",
                    productRequest.code(),
                    productRequest.quantity()
            );
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

    private void updateInventory(WebClient.RequestBodyUriSpec webClientBuilder, String url, String productRequest, Integer productRequest1) {
        webClientBuilder
                .uri(url,
                        uriBuilder -> uriBuilder
                                .queryParam("code", productRequest)
                                .queryParam("quantity", productRequest1)
                                .build())
                .retrieve()
                .toBodilessEntity()
                .block();
    }
}
