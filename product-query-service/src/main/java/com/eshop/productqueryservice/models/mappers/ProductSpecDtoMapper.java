package com.eshop.productqueryservice.models.mappers;

import com.eshop.productqueryservice.models.dto.ProductSpecDto;
import com.eshop.productqueryservice.models.dto.specDtos.BodyDto;
import com.eshop.productqueryservice.models.dto.specDtos.BootsDto;
import com.eshop.productqueryservice.models.dto.specDtos.HeadDto;
import com.eshop.productqueryservice.models.dto.specDtos.PantsDto;
import com.eshop.productqueryservice.models.entity.ProductSpec;
import com.eshop.productqueryservice.models.entity.specsEntitty.BodySpec;
import com.eshop.productqueryservice.models.entity.specsEntitty.BootsSpec;
import com.eshop.productqueryservice.models.entity.specsEntitty.HeadSpec;
import com.eshop.productqueryservice.models.entity.specsEntitty.PantsSpec;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class ProductSpecDtoMapper implements Function<ProductSpec, ProductSpecDto> {
    @Override
    public ProductSpecDto apply(ProductSpec productSpec) {
        if (productSpec instanceof BodySpec bodySpec) {
            return BodyDto.builder()
                    .size(bodySpec.getSize())
                    .color(bodySpec.getColor())
                    .sex(bodySpec.getSex())
                    .sleeve(bodySpec.getSleeve())
                    .build();
        } else if (productSpec instanceof BootsSpec bootsSpec) {
            return BootsDto.builder()
                    .size(bootsSpec.getSize())
                    .color(bootsSpec.getColor())
                    .sex(bootsSpec.getSex())
                    .liftingHeight(bootsSpec.getLiftingHeight())
                    .build();
        } else if (productSpec instanceof HeadSpec headSpec) {
            return HeadDto.builder()
                    .size(headSpec.getSize())
                    .color(headSpec.getColor())
                    .sex(headSpec.getSex())
                    .headGirth(headSpec.getHeadGirth())
                    .build();
        } else if (productSpec instanceof PantsSpec pantsSpec) {
            return PantsDto.builder()
                    .size(pantsSpec.getSize())
                    .color(pantsSpec.getColor())
                    .sex(pantsSpec.getSex())
                    .pantLength(pantsSpec.getPantLength())
                    .build();
        }
        return null;
    }
}
