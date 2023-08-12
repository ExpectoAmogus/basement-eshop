package com.eshop.productservice.service.impl;

import com.eshop.productservice.models.dto.ProductSpecDto;
import com.eshop.productservice.models.dto.specDtos.BodyDto;
import com.eshop.productservice.models.dto.specDtos.BootsDto;
import com.eshop.productservice.models.dto.specDtos.HeadDto;
import com.eshop.productservice.models.dto.specDtos.PantsDto;
import com.eshop.productservice.models.entity.ProductSpec;
import com.eshop.productservice.models.mappers.ProductSpecDtoMapper;
import com.eshop.productservice.models.mappers.ProductSpecMapper;
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
    private final ProductSpecMapper productSpecMapper;

    public ProductSpecDto createSpec(ProductSpecDto productSpecDto) {
        ProductSpecDto spec = null;
        if (productSpecDto instanceof BodyDto bodySpec) {
            spec = BodyDto.builder()
                    .size(bodySpec.getSize())
                    .color(bodySpec.getColor())
                    .sex(bodySpec.getSex())
                    .sleeve(bodySpec.getSleeve())
                    .build();
        } else if (productSpecDto instanceof BootsDto bootsSpec) {
            spec = BootsDto.builder()
                    .size(bootsSpec.getSize())
                    .color(bootsSpec.getColor())
                    .sex(bootsSpec.getSex())
                    .liftingHeight(bootsSpec.getLiftingHeight())
                    .build();
        } else if (productSpecDto instanceof HeadDto headSpec) {
            spec = HeadDto.builder()
                    .size(headSpec.getSize())
                    .color(headSpec.getColor())
                    .sex(headSpec.getSex())
                    .headGirth(headSpec.getHeadGirth())
                    .build();
        } else if (productSpecDto instanceof PantsDto pantsSpec) {
            spec = PantsDto.builder()
                    .size(pantsSpec.getSize())
                    .color(pantsSpec.getColor())
                    .sex(pantsSpec.getSex())
                    .pantLength(pantsSpec.getPantLength())
                    .build();
        }
        ProductSpec productSpec = productSpecRepository.save(productSpecMapper.apply(spec));
        log.info("Spec is saved");
        return productSpecDtoMapper.apply(productSpec);
    }

    public ProductSpecDto findById(Long id) {
        return productSpecRepository.findById(id)
                .map(productSpecDtoMapper)
                .orElseThrow(EntityNotFoundException::new);
    }
}
