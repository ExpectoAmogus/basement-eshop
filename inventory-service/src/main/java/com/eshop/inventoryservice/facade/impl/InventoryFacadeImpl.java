package com.eshop.inventoryservice.facade.impl;

import com.eshop.inventoryservice.facade.InventoryFacade;
import com.eshop.inventoryservice.models.dto.InventoryRequest;
import com.eshop.inventoryservice.models.dto.InventoryResponse;
import com.eshop.inventoryservice.service.InventoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class InventoryFacadeImpl implements InventoryFacade {
    private final InventoryService inventoryService;

    @Override
    public List<InventoryResponse> isInStock(List<String> code) {
        return inventoryService.isInStock(code);
    }

    @Override
    public void create(InventoryRequest request) {
        inventoryService.create(request.getCode(), request.getQuantity());
    }

    @Override
    public void delete(String code) {
        inventoryService.delete(code);
    }

    @Override
    public void update(InventoryRequest request) {
        inventoryService.update(request.getCode(), request.getQuantity());
    }
}
