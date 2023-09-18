package com.eshop.productcommandservice.models.mappers;

import com.eshop.productcommandservice.models.dto.ProductCategoryDto;
import com.eshop.productcommandservice.models.entity.ProductCategory;
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
