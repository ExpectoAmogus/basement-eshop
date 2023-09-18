package com.eshop.productcommandservice.models.dto;

public record ProductCategoryDto(
        String id,
        String name,
        String parentId
) {
}
