package com.eshop.productqueryservice.models.dto.specDtos;

import com.eshop.productqueryservice.models.dto.ProductSpecDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class BootsDto extends ProductSpecDto {

    private Long liftingHeight;
    private String soleType;
    private String clasp;
}
