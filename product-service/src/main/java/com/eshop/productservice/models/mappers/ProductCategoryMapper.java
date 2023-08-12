package com.eshop.productservice.models.mappers;

import com.eshop.productservice.models.dto.ProductCategoryDto;
import com.eshop.productservice.models.entity.ProductCategory;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class ProductCategoryMapper implements Function<ProductCategoryDto, ProductCategory> {
    @Override
    public ProductCategory apply(ProductCategoryDto productCategoryDto) {
        return ProductCategory.builder()
                .id(productCategoryDto.id())
                .name(productCategoryDto.name())
                .parentId(productCategoryDto.parentId())
                .build();
    }
}
