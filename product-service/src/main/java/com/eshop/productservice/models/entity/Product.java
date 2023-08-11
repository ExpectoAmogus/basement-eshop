package com.eshop.productservice.models.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@Entity
@Getter
@Setter
@Table(name = "products")
@Builder
@RequiredArgsConstructor
public class Product extends BaseEntity {

    @Column(name = "p_code")
    private String code;

    @Column(name = "p_name")
    private String name;

    @Column(name = "p_desc", columnDefinition = "text")
    private String description;

    @OneToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinColumn
    @JsonManagedReference
    private ProductSpec spec;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    @JsonBackReference
    private ProductCategory category;

    @Column(name = "p_price")
    private BigDecimal price;
}
