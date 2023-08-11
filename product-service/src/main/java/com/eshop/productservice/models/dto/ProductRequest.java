package com.eshop.productservice.models.dto;

import com.eshop.productservice.models.entity.ProductCategory;
import com.eshop.productservice.models.entity.ProductSpec;

import java.math.BigDecimal;

public record ProductRequest(
        String name,
        String code,
        String description,
        ProductCategory category,
        ProductSpec spec,
        BigDecimal price
) {
}
