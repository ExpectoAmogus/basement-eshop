package com.eshop.productqueryservice.models.dto;

import com.eshop.productqueryservice.models.dto.specDtos.BodyDto;
import com.eshop.productqueryservice.models.dto.specDtos.BootsDto;
import com.eshop.productqueryservice.models.dto.specDtos.HeadDto;
import com.eshop.productqueryservice.models.dto.specDtos.PantsDto;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
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
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = BodyDto.class, name = "BODY"),
        @JsonSubTypes.Type(value = BootsDto.class, name = "BOOTS"),
        @JsonSubTypes.Type(value = HeadDto.class, name = "HEAD"),
        @JsonSubTypes.Type(value = PantsDto.class, name = "PANTS")
})
@JsonTypeName("ProductSpecDto")
public abstract class ProductSpecDto {
    String size;
    String color;
    String sex;
    String material;
    String season;
    String style;
}
