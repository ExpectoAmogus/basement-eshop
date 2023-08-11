package com.eshop.productservice.models.entity;

import com.eshop.productservice.models.enums.Category;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
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
    @Column(name = "p_category")
    @Enumerated(EnumType.STRING)
    private Category category;
}
