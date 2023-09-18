package com.eshop.productcommandservice.controller;

import com.eshop.productcommandservice.facade.ProductFacade;
import com.eshop.productcommandservice.models.dto.ProductCreateResponse;
import com.eshop.productcommandservice.models.dto.ProductRequest;
import com.eshop.productcommandservice.models.dto.ProductToUpdateRequest;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/product")
public class ProductController {
    private final ProductFacade productFacade;

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public ProductCreateResponse create(@RequestBody ProductRequest productRequest, HttpServletRequest request) {
        return productFacade.createProduct(productRequest, request);
    }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    public void update(@RequestBody ProductToUpdateRequest updateRequest, HttpServletRequest request) {
        productFacade.updateProduct(updateRequest, request);
    }

    @DeleteMapping("/delete/{code}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable String code){
        productFacade.deleteProduct(code);
    }
}
