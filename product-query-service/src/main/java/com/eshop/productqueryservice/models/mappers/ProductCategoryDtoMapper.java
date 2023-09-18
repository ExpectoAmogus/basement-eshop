package com.eshop.productqueryservice.models.mappers;

import com.eshop.productqueryservice.models.dto.ProductCategoryDto;
import com.eshop.productqueryservice.models.entity.ProductCategory;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class ProductCategoryDtoMapper implements Function<ProductCategory, ProductCategoryDto> {

    @Override
    public ProductCategoryDto apply(ProductCategory productCategory) {
        return new ProductCategoryDto(
                productCategory.getId(),
                productCategory.getName(),
                productCategory.getParentId()
        );
    }
}
