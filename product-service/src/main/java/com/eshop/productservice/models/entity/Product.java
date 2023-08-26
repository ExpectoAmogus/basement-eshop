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
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@Table(name = "products")
@SuperBuilder
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
}
