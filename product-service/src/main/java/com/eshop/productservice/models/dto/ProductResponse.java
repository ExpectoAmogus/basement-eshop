package com.eshop.productservice.models.dto;

import java.math.BigDecimal;

public record ProductResponse(
        Long id,
        String name,
        String code,
        String description,
        ProductCategoryDto category,
        ProductSpecDto spec,
        BigDecimal price
) {

}
