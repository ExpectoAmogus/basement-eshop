package com.eshop.productservice.models.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "product_category")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "c_name")
    private String name;

    @Column(name = "parent_id")
    private Long parentId;
}
