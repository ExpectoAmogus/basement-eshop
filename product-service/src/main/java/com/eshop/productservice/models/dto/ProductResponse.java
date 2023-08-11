package com.eshop.productservice.models.dto;

import com.eshop.productservice.models.entity.ProductSpec;

import java.math.BigDecimal;

public record ProductResponse(
        Long id,
        String name,
        String code,
        String description,
        ProductCategoryResponse category,
        ProductSpec spec,
        BigDecimal price
) {

}
