package com.eshop.inventoryservice.models.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InventoryResponse {
    private String code;
    private boolean isInStock;
}
