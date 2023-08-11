package com.eshop.productservice.models.dto;

import com.eshop.productservice.models.entity.ProductCategory;
import com.eshop.productservice.models.entity.ProductSpec;

import java.math.BigDecimal;

public record ProductResponse(
        Long id,
        String name,
        String code,
        String description,
        ProductCategory category,
        ProductSpec spec,
        BigDecimal price
) {

}
