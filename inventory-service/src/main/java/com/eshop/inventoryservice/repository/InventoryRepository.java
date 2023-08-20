package com.eshop.inventoryservice.repository;

import com.eshop.inventoryservice.models.entity.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {
    List<Inventory> findByCodeIn(List<String> code);
    Inventory findByCode(String code);
}
