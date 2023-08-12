package com.eshop.productservice.models.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@Setter
@Table(name = "specs")
@SuperBuilder
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "spec_type", discriminatorType = DiscriminatorType.STRING)
public class ProductSpec {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //all
    @Column(name = "ps_size")
    private String size;
    @Column(name = "ps_color")
    private String color;
    @Column(name = "ps_sex")
    private String sex;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "spec")
    @JsonManagedReference
    private Product product;

    public ProductSpec(){
        super();
    }
}
