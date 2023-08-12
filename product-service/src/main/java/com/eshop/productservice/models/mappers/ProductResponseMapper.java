package com.eshop.productservice.models.mappers;

import com.eshop.productservice.models.dto.ProductResponse;
import com.eshop.productservice.models.entity.Product;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class ProductResponseMapper implements Function<Product, ProductResponse> {
    @Override
    public ProductResponse apply(Product product) {
        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getCode(),
                product.getDescription(),
                product.getProductCategoryDto(),
                product.getSpec(),
                product.getPrice()
                );
    }
}
