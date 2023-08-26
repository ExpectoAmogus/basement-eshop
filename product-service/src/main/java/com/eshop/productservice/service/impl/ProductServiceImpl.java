package com.eshop.productservice.service.impl;

import com.eshop.productservice.models.entity.Product;
import com.eshop.productservice.models.entity.ProductCategory;
import com.eshop.productservice.repositories.ProductRepository;
import com.eshop.productservice.service.ProductCategoryService;
import com.eshop.productservice.service.ProductService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductCategoryService categoryService;

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

        log.info("Product {} is created", product.getId());
        return productRepository.save(product);
    }

    @Override
    public void updateProduct(Product product) {
        product = Product.builder()
                .id(product.getId())
                .code(product.getCode())
                .name(product.getName())
                .description(product.getDescription())
                .spec(product.getSpec())
                .category(product.getCategory())
                .price(product.getPrice())
                .build();
        productRepository.save(product);

        log.info("Product {} is updated", product.getId());
    }

    @Override
    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product findById(Long id) {
        return productRepository.findById(id)
                        .orElseThrow(() -> new EntityNotFoundException("Product does not exist!"));
    }

    private boolean categoryExists(Product product) {
        return categoryService.getAllCategories()
                .stream()
                .map(ProductCategory::getName)
                .toList()
                .contains(product.getCategory().getName());
    }

}
