package com.eshop.productcommandservice.models.mappers;

import com.eshop.productcommandservice.models.dto.ProductResponse;
import com.eshop.productcommandservice.models.entity.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class ProductResponseMapper implements Function<Product, ProductResponse> {
    private final ProductSpecDtoMapper productSpecDtoMapper;
    private final ProductCategoryDtoMapper productCategoryDtoMapper;

    @Override
    public ProductResponse apply(Product product) {
        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getCode(),
                product.getDescription(),
                productCategoryDtoMapper.apply(product.getCategory()),
                productSpecDtoMapper.apply(product.getSpec()),
                product.getPrice()
                );
    }
}
