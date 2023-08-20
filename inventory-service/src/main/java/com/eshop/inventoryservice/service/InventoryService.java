package com.eshop.inventoryservice.service;

import com.eshop.inventoryservice.models.dto.InventoryResponse;

import java.util.List;

public interface InventoryService {
    List<InventoryResponse> isInStock(List<String> code);
    void create(String code, Integer quantity);
    void delete(String code);
    void update(String code, Integer quantity);
}
