package com.eshop.orderservice.facade;

import com.eshop.orderservice.models.dto.OrderRequest;

public interface OrderFacade {
    String placeOrder(OrderRequest orderRequest);
}
