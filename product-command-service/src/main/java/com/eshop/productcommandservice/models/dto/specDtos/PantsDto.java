package com.eshop.productcommandservice.models.dto.specDtos;

import com.eshop.productcommandservice.models.dto.ProductSpecDto;
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
public class PantsDto extends ProductSpecDto {

    private Long pantLength;
}
