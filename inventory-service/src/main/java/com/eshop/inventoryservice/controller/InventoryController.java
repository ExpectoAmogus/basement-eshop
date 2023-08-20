package com.eshop.inventoryservice.controller;

import com.eshop.inventoryservice.models.dto.InventoryResponse;
import com.eshop.inventoryservice.service.InventoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("api/inventory")
public class InventoryController {
    private final InventoryService inventoryService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<InventoryResponse> isInStock(@RequestParam List<String> code) {
        log.info("Received inventory check request for code: {}", code);
        return inventoryService.isInStock(code);
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestParam String code, @RequestParam Integer quantity){
        inventoryService.create(code, quantity);
        log.info("Created entity with code {}", code);
    }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    public void update(@RequestParam String code, @RequestParam Integer quantity){
        inventoryService.update(code, quantity);
        log.info("Updated entity with code {}", code);
    }

    @DeleteMapping("/delete")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@RequestParam String code){
        inventoryService.delete(code);
        log.info("Deleted entity with code {}", code);
    }
}
