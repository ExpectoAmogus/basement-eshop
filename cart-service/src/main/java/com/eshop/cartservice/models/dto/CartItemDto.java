package com.eshop.cartservice.models.dto;

public record CartItemDto(
        String productId,
        int quantity
) {
}
