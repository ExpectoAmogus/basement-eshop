package com.eshop.orderservice.models.dto;

import java.math.BigDecimal;

public record OrderItemDto(
        Long id,
        String code,
        BigDecimal price,
        Integer quantity
) {
}
