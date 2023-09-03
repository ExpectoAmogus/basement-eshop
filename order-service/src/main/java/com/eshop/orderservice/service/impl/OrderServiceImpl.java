package com.eshop.orderservice.service.impl;

import com.eshop.orderservice.models.entity.Order;
import com.eshop.orderservice.repository.OrderRepository;
import com.eshop.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    @Override
    public String placeOrder(Order order) {
        orderRepository.save(order);
        log.info("Order Placed!");
        return "Order Placed!";
    }
}
