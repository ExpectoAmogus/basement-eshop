package com.eshop.inventoryservice.facade;

import com.eshop.inventoryservice.models.dto.InventoryRequest;
import com.eshop.inventoryservice.models.dto.InventoryResponse;

import java.util.List;

public interface InventoryFacade {
    List<InventoryResponse> isInStock(List<String> code);
    void create(InventoryRequest request);
    void delete(String code);
    void update(InventoryRequest request);
}
