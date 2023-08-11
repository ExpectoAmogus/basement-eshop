package com.eshop.productservice.models.mappers;

import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class ProductCategoryMapper<T,R> implements Function<T,R> {
    private final Function<T, R> mappingFunction;

    public ProductCategoryMapper(Function<T, R> mappingFunction) {
        this.mappingFunction = mappingFunction;
    }
    @Override
    public R apply(T t) {
        return mappingFunction.apply(t);
    }
}
