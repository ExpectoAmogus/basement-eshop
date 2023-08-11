package com.eshop.productservice.controller;

import com.eshop.productservice.models.dto.ProductResponse;
import com.eshop.productservice.service.impl.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/products")
public class ProductController {
    private final ProductService productService;

    @GetMapping("/all")
    public ResponseEntity<List<ProductResponse>> getProducts(){
        return ResponseEntity.ok()
                .body(productService.getProducts());
    }
}
