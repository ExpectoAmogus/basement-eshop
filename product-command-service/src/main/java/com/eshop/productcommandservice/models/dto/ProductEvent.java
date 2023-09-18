package com.eshop.productcommandservice.models.dto;

import com.eshop.productcommandservice.models.entity.Product;
import com.eshop.productcommandservice.models.enums.EventType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductEvent {
    private EventType eventType;
    private Product product;
}
