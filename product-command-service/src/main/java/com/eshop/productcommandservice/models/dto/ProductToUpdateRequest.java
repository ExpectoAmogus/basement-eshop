package com.eshop.productcommandservice.models.dto;

import java.math.BigDecimal;

public record ProductToUpdateRequest(
        String id,
        String name,
        String code,
        String description,
        ProductCategoryDto category,
        ProductSpecDto spec,
        BigDecimal price,
        Integer quantity
) {
}
