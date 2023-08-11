package com.eshop.productservice.models.dto;

import com.eshop.productservice.models.entity.ProductSpec;
import com.eshop.productservice.models.enums.Category;

public record ProductResponse(
        Long id,
        String name,
        String code,
        Category category,
        ProductSpec spec
) {

}
