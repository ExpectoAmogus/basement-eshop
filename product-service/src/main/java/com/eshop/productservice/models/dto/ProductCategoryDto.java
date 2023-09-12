package com.eshop.productservice.models.dto;

public record ProductCategoryDto(
        String id,
        String name,
        String parentId
) {
}
