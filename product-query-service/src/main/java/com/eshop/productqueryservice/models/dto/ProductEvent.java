package com.eshop.productqueryservice.models.dto;

import com.eshop.productqueryservice.models.entity.Product;
import com.eshop.productqueryservice.models.enums.EventType;
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
