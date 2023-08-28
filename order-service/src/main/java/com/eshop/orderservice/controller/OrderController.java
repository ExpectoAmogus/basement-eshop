package com.eshop.orderservice.controller;

import com.eshop.orderservice.facade.OrderFacade;
import com.eshop.orderservice.models.dto.OrderRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/order")
@RequiredArgsConstructor
public class OrderController {
    private final OrderFacade orderFacade;
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String placeOrder(@RequestBody OrderRequest orderRequest){
        orderFacade.placeOrder(orderRequest);
        return "Order placed!";
    }
}
