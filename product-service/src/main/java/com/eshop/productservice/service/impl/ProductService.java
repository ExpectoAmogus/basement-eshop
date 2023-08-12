package com.eshop.productservice.service.impl;

import com.eshop.productservice.models.dto.ProductRequest;
import com.eshop.productservice.models.dto.ProductResponse;
import com.eshop.productservice.models.entity.Product;
import com.eshop.productservice.models.entity.ProductCategory;
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

    public void createProduct(ProductRequest productRequest){
//        ProductCategoryResponse categoryResponse;
        ProductCategory category;
        if (!categoryExists(productRequest)){
            log.warn("Could not find category {}, creating new one...", productRequest.category().name());
            category = categoryService.createCategory(productRequest.category().name(), productRequest.category().parent());
        }
        else {
//            categoryResponse = categoryService.findById(productRequest.category().id());
            category = ProductCategory.builder()
                    .id(productRequest.category().id())
                    .name(productRequest.category().name())
                    .parent(productRequest.category().parent())
                    .build();
        }

        Product product = Product.builder()
                .name(productRequest.name())
                .code(productRequest.code())
                .description(productRequest.description())
                .spec(productRequest.spec())
                .category(category)
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

    private boolean categoryExists(ProductRequest productRequest) {
        return categoryService.getAllCategories()
                .contains(productRequest.category());
    }

}
