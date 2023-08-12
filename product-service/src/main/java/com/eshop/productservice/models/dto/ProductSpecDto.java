package com.eshop.productservice.models.dto;

public record ProductSpecDto(
        String size,
        String color,
        String sex,
        String sleeve,
        Long pantLength,
        Long headGirth,
        Long liftingHeight
) {
}
