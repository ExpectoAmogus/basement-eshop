package com.eshop.orderservice.service;

import com.eshop.orderservice.models.entity.Order;

public interface OrderService {
    String placeOrder(Order order);

}
