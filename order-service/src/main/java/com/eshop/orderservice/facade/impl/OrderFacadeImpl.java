package com.eshop.orderservice.facade.impl;

import com.eshop.orderservice.facade.OrderFacade;
import com.eshop.orderservice.models.dto.InventoryResponse;
import com.eshop.orderservice.models.dto.OrderRequest;
import com.eshop.orderservice.models.entity.Order;
import com.eshop.orderservice.models.entity.OrderItem;
import com.eshop.orderservice.models.mapper.OrderItemMapper;
import com.eshop.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class OrderFacadeImpl implements OrderFacade {

    private final OrderService orderService;
    private final OrderItemMapper orderItemMapper;
    private final WebClient.Builder webClientBuilder;

    @Override
    public String placeOrder(OrderRequest orderRequest) {
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());

        List<OrderItem> orderItems = orderRequest.orderItemDtoList()
                .stream()
                .map(orderItemMapper)
                .toList();

        order.setOrderItemList(orderItems);

        List<String> codes = order.getOrderItemList().stream()
                .map(OrderItem::getCode)
                .toList();

        InventoryResponse[] inventoryResponses = webClientBuilder.build().get()
                .uri("http://inventory-service/api/inventory",
                        uriBuilder -> uriBuilder.queryParam("code", codes).build())
                .retrieve()
                .bodyToMono(InventoryResponse[].class)
                .block();

        assert inventoryResponses != null;
        boolean allProductsInStock = Arrays.stream(inventoryResponses)
                .allMatch(InventoryResponse::isInStock);

        if (allProductsInStock) {
            return orderService.placeOrder(order);
        } else {
            throw new IllegalArgumentException("Product is not in stock, please try again later");
        }
    }
}
