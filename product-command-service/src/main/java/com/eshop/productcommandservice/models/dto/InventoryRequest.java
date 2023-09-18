package com.eshop.productcommandservice.models.dto;

import com.eshop.productcommandservice.models.enums.EventType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
@NoArgsConstructor
public class InventoryRequest {
    private EventType eventType;
    private String code;
    private Integer quantity;
}
