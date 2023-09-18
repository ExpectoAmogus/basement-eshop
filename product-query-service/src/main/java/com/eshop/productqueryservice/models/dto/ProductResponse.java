package com.eshop.productqueryservice.models.dto;

import java.math.BigDecimal;

public record ProductResponse(
        String id,
        String name,
        String code,
        String description,
        ProductCategoryDto category,
        ProductSpecDto spec,
        BigDecimal price
) {

}
