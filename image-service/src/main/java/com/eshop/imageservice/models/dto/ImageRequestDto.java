package com.eshop.imageservice.models.dto;

public record ImageRequestDto(
        String entityType,
        Long entityId
) {
}
