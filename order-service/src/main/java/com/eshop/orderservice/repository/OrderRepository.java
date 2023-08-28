package com.eshop.orderservice.repository;

import com.eshop.orderservice.models.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
