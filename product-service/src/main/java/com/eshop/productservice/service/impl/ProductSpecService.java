package com.eshop.productservice.service.impl;

import com.eshop.productservice.models.dto.ProductCategoryDto;
import com.eshop.productservice.models.dto.ProductSpecDto;
import com.eshop.productservice.models.entity.ProductCategory;
import com.eshop.productservice.models.entity.ProductSpec;
import com.eshop.productservice.models.mappers.ProductSpecDtoMapper;
import com.eshop.productservice.repositories.ProductSpecRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductSpecService {
    private final ProductSpecRepository productSpecRepository;
    private final ProductSpecDtoMapper productSpecDtoMapper;

    public ProductSpec createSpec(ProductSpecDto productSpecDto){
        ProductSpec spec = ProductSpec.builder()
                .size(productSpecDto.size())
                .color(productSpecDto.color())
                .sex(productSpecDto.sex())
                .sleeve(productSpecDto.sleeve())
                .headGirth(productSpecDto.headGirth())
                .pantLength(productSpecDto.pantLength())
                .liftingHeight(productSpecDto.liftingHeight())
                .build();

        log.info("Spec is saved");
        return productSpecRepository.save(spec);
    }

    public ProductSpecDto findById(Long id){
        return productSpecRepository.findById(id)
                .map(productSpecDtoMapper)
                .orElseThrow(EntityNotFoundException::new);
    }
}
