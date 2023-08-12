package com.eshop.productservice.models.dto;

public record ProductCategoryDto(
        Long id,
        String name,
        Long parentId
) {
}
