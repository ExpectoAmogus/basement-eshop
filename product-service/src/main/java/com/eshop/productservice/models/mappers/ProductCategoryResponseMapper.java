package com.eshop.productservice.models.mappers;

import com.eshop.productservice.models.dto.ProductCategoryResponse;
import com.eshop.productservice.models.entity.ProductCategory;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class ProductCategoryResponseMapper implements Function<ProductCategory, ProductCategoryResponse> {
    @Override
    public ProductCategoryResponse apply(ProductCategory productCategory) {
        return new ProductCategoryResponse(
                productCategory.getId(),
                productCategory.getName(),
                productCategory.getParent()
        );
    }
}
