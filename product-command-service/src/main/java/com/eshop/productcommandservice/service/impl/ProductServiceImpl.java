package com.eshop.productcommandservice.service.impl;

import com.eshop.productcommandservice.models.dto.ProductEvent;
import com.eshop.productcommandservice.models.entity.Product;
import com.eshop.productcommandservice.models.entity.ProductCategory;
import com.eshop.productcommandservice.models.enums.EventType;
import com.eshop.productcommandservice.repositories.ProductRepository;
import com.eshop.productcommandservice.service.ProductCategoryService;
import com.eshop.productcommandservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductCategoryService categoryService;
    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Override
    public Product createProduct(Product product) {
        ProductCategory category;
        if (!categoryExists(product)) {
            log.warn("Could not find category {}, creating new one...", product.getCategory().getName());
            category = categoryService.createCategory(product.getCategory());
        } else {
            category = categoryService.findByNameAndParentId(product.getCategory().getName(), product.getCategory().getParentId());
        }

        product = Product.builder()
                .name(product.getName())
                .code(product.getCode())
                .description(product.getDescription())
                .spec(product.getSpec())
                .category(category)
                .price(product.getPrice())
                .build();
        Product newProduct = productRepository.save(product);
        //send event to query microservice
        kafkaTemplate.send("product-query-topic", new ProductEvent(
                EventType.PRODUCT_CREATED,
                newProduct
        ));
        log.info("Product {} is created", newProduct.getId());
        return newProduct;
    }

    @Override
    public void updateProduct(Product product) {
        Product updatedProduct = productRepository.save(product);
        //send event to query microservice
        kafkaTemplate.send("product-query-topic", new ProductEvent(
                EventType.PRODUCT_UPDATED,
                updatedProduct
        ));
        log.info("Product {} is updated", product.getId());
    }

    @Override
    public void deleteProduct(Product product) {
        //send event to query microservice
        kafkaTemplate.send("product-query-topic", new ProductEvent(
                EventType.PRODUCT_DELETED,
                product
        ));
        productRepository.delete(product);
        log.info("Product {} is deleted", product.getId());
    }

    @Override
    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product findById(String id) {
        return productRepository.findById(id)
                .orElseThrow();
    }

    @Override
    public Product findByCode(String code) {
        return productRepository.findByCode(code)
                .orElseThrow();
    }

    private boolean categoryExists(Product product) {
        return categoryService.getAllCategories()
                .stream()
                .map(ProductCategory::getName)
                .toList()
                .contains(product.getCategory().getName());
    }

}
