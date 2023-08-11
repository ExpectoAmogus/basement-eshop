package com.eshop.productservice.service;

import com.eshop.productservice.models.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
