package com.eshop.inventoryservice.service.impl;

import com.eshop.inventoryservice.models.dto.InventoryResponse;
import com.eshop.inventoryservice.models.entity.Inventory;
import com.eshop.inventoryservice.repository.InventoryRepository;
import com.eshop.inventoryservice.service.InventoryService;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class InventoryServiceImpl implements InventoryService {

    private final InventoryRepository inventoryRepository;
    @Override
    @Transactional(readOnly = true)
    @SneakyThrows
    public List<InventoryResponse> isInStock(List<String> code) {
        log.info("Checking Inventory");
        return inventoryRepository.findByCodeIn(code).stream()
                .map(inventory ->
                        InventoryResponse.builder()
                                .code(inventory.getCode())
                                .isInStock(inventory.getQuantity() > 0)
                                .build()
                ).toList();
    }

    @Override
    @Transactional
    public void create(String code, Integer quantity) {
        Inventory inventory = Inventory.builder()
                .code(code)
                .quantity(quantity)
                .build();
        inventoryRepository.save(inventory);
        log.info("Product has been added to stock!");
    }

    @Override
    @Transactional
    public void delete(String code) {
        Inventory inventory = inventoryRepository.findByCode(code);
        inventoryRepository.delete(inventory);
        log.info("Product has been deleted from stock!");
    }

    @Override
    @Transactional
    public void update(String code, Integer quantity) {
        Inventory inventory = inventoryRepository.findByCode(code);
        inventory = Inventory.builder()
                .id(inventory.getId())
                .code(code)
                .quantity(quantity)
                .build();
        inventoryRepository.save(inventory);
        log.info("Product has been updated in stock!");
    }
}
