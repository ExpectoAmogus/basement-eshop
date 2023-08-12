package com.eshop.productservice.models.mappers;

import com.eshop.productservice.models.dto.ProductSpecDto;
import com.eshop.productservice.models.entity.ProductSpec;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class ProductSpecDtoMapper implements Function<ProductSpec, ProductSpecDto> {
    @Override
    public ProductSpecDto apply(ProductSpec productSpec) {
        return new ProductSpecDto(
                productSpec.getSize(),
                productSpec.getColor(),
                productSpec.getSex(),
                productSpec.getSleeve(),
                productSpec.getPantLength(),
                productSpec.getHeadGirth(),
                productSpec.getLiftingHeight()
        );
    }
}
