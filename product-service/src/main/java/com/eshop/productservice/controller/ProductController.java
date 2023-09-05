package com.eshop.productservice.controller;

import com.eshop.productservice.facade.ProductFacade;
import com.eshop.productservice.models.dto.ProductCreateResponse;
import com.eshop.productservice.models.dto.ProductRequest;
import com.eshop.productservice.models.dto.ProductResponse;
import com.eshop.productservice.models.dto.ProductToUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/product")
public class ProductController {
    private final ProductFacade productFacade;

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<ProductResponse> getAll() {
        return productFacade.getProducts();
    }

    @GetMapping("/get/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ProductResponse get(@PathVariable Long id) {
        return productFacade.findById(id);
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('ADMIN')")
    public ProductCreateResponse create(@RequestBody ProductRequest productRequest) {
        return productFacade.createProduct(productRequest);
    }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('ADMIN')")
    public void update(@RequestBody ProductToUpdateRequest updateRequest) {
        productFacade.updateProduct(updateRequest);
    }
}
