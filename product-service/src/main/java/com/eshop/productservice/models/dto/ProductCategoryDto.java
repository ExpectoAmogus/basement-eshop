package com.eshop.productservice.models.dto;

import com.eshop.productservice.models.entity.ProductCategory;

public record ProductCategoryDto(
        Long id,
        String name,
        ProductCategory parent
) {
}
