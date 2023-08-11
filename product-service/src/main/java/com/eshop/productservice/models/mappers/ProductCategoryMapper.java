package com.eshop.productservice.models.mappers;

import com.eshop.productservice.models.dto.ProductCategoryResponse;
import com.eshop.productservice.models.entity.ProductCategory;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class ProductCategoryMapper implements Function<ProductCategoryResponse, ProductCategory> {
    @Override
    public ProductCategory apply(ProductCategoryResponse productCategoryResponse) {
        return ProductCategory.builder()
                .id(productCategoryResponse.id())
                .name(productCategoryResponse.name())
                .parent(productCategoryResponse.parent())
                .build();
    }
}
