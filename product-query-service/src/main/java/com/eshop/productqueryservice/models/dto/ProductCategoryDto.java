package com.eshop.productqueryservice.models.dto;

public record ProductCategoryDto(
        String id,
        String name,
        String parentId
) {
}
