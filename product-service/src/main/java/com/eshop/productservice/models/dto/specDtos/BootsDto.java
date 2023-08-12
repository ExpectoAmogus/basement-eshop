package com.eshop.productservice.models.dto.specDtos;

import com.eshop.productservice.models.dto.ProductSpecDto;
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
}
