package com.eshop.cartservice.models.dto;

import java.util.List;

public record CartDto(
        String userId,
        List<CartItemDto> items
) {
}
