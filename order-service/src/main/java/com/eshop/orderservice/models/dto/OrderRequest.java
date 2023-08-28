package com.eshop.orderservice.models.dto;

import java.util.List;

public record OrderRequest(
        List<OrderItemDto> orderItemDtoList
) {
}
