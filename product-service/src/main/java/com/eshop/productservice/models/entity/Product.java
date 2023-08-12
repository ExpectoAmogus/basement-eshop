package com.eshop.productservice.models.entity;

import com.eshop.productservice.models.dto.ProductCategoryDto;
import com.eshop.productservice.models.dto.ProductSpecDto;
import com.eshop.productservice.models.dto.specDtos.BodyDto;
import com.eshop.productservice.models.dto.specDtos.BootsDto;
import com.eshop.productservice.models.dto.specDtos.HeadDto;
import com.eshop.productservice.models.dto.specDtos.PantsDto;
import com.eshop.productservice.models.entity.specsEntitty.BodySpec;
import com.eshop.productservice.models.entity.specsEntitty.BootsSpec;
import com.eshop.productservice.models.entity.specsEntitty.HeadSpec;
import com.eshop.productservice.models.entity.specsEntitty.PantsSpec;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@Entity
@Getter
@Setter
@Table(name = "products")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Product extends BaseEntity {

    @Column(name = "p_code", unique = true, nullable = false)
    private String code;

    @Column(name = "p_name", nullable = false)
    private String name;

    @Column(name = "p_desc", columnDefinition = "text")
    private String description;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn
    @JsonBackReference
    private ProductSpec spec;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    @JsonBackReference
    private ProductCategory category;

    @Column(name = "p_price", nullable = false)
    private BigDecimal price;

    public ProductCategoryDto getProductCategoryDto() {
        return new ProductCategoryDto(
                category.getId(),
                category.getName(),
                category.getParentId()
        );
    }

    public ProductSpecDto getProductSpecDto() {
        if (spec instanceof BodySpec bodySpec) {
            return BodyDto.builder()
                    .size(bodySpec.getSize())
                    .color(bodySpec.getColor())
                    .sex(bodySpec.getSex())
                    .sleeve(bodySpec.getSleeve())
                    .build();
        } else if (spec instanceof BootsSpec bootsSpec) {
            return BootsDto.builder()
                    .size(bootsSpec.getSize())
                    .color(bootsSpec.getColor())
                    .sex(bootsSpec.getSex())
                    .liftingHeight(bootsSpec.getLiftingHeight())
                    .build();
        } else if (spec instanceof HeadSpec headSpec) {
            return HeadDto.builder()
                    .size(headSpec.getSize())
                    .color(headSpec.getColor())
                    .sex(headSpec.getSex())
                    .headGirth(headSpec.getHeadGirth())
                    .build();
        } else if (spec instanceof PantsSpec pantsSpec) {
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
