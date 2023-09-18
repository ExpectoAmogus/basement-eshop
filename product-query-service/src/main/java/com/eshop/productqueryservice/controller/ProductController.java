package com.eshop.productqueryservice.controller;

import com.eshop.productqueryservice.facade.ProductFacade;
import com.eshop.productqueryservice.models.dto.ProductResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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
    public ProductResponse get(@PathVariable String id) {
        return productFacade.findById(id);
    }
}
