package com.eshop.orderservice.models.mapper;

import com.eshop.orderservice.models.dto.OrderItemDto;
import com.eshop.orderservice.models.entity.OrderItem;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class OrderItemMapper implements Function<OrderItemDto, OrderItem> {
    @Override
    public OrderItem apply(OrderItemDto orderItemDto) {
        return new OrderItem(
                orderItemDto.id(),
                orderItemDto.code(),
                orderItemDto.price(),
                orderItemDto.quantity()
        );
    }
}
