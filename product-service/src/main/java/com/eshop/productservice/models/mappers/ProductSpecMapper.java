package com.eshop.productservice.models.mappers;

import com.eshop.productservice.models.dto.ProductSpecDto;
import com.eshop.productservice.models.dto.specDtos.BodyDto;
import com.eshop.productservice.models.dto.specDtos.BootsDto;
import com.eshop.productservice.models.dto.specDtos.HeadDto;
import com.eshop.productservice.models.dto.specDtos.PantsDto;
import com.eshop.productservice.models.entity.ProductSpec;
import com.eshop.productservice.models.entity.specsEntitty.BodySpec;
import com.eshop.productservice.models.entity.specsEntitty.BootsSpec;
import com.eshop.productservice.models.entity.specsEntitty.HeadSpec;
import com.eshop.productservice.models.entity.specsEntitty.PantsSpec;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class ProductSpecMapper implements Function<ProductSpecDto, ProductSpec> {
    @Override
    public ProductSpec apply(ProductSpecDto productSpecDto) {
        if (productSpecDto instanceof BodyDto bodySpec) {
            return BodySpec.builder()
                    .size(bodySpec.getSize())
                    .color(bodySpec.getColor())
                    .sex(bodySpec.getSex())
                    .sleeve(bodySpec.getSleeve())
                    .build();
        } else if (productSpecDto instanceof BootsDto bootsSpec) {
            return BootsSpec.builder()
                    .size(bootsSpec.getSize())
                    .color(bootsSpec.getColor())
                    .sex(bootsSpec.getSex())
                    .liftingHeight(bootsSpec.getLiftingHeight())
                    .build();
        } else if (productSpecDto instanceof HeadDto headSpec) {
            return HeadSpec.builder()
                    .size(headSpec.getSize())
                    .color(headSpec.getColor())
                    .sex(headSpec.getSex())
                    .headGirth(headSpec.getHeadGirth())
                    .build();
        } else if (productSpecDto instanceof PantsDto pantsSpec) {
            return PantsSpec.builder()
                    .size(pantsSpec.getSize())
                    .color(pantsSpec.getColor())
                    .sex(pantsSpec.getSex())
                    .pantLength(pantsSpec.getPantLength())
                    .build();
        }

        return ProductSpec.builder()
                .size(productSpecDto.getSize())
                .color(productSpecDto.getColor())
                .sex(productSpecDto.getSex())
                .build();
    }
}
