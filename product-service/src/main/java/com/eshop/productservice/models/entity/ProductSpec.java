package com.eshop.productservice.models.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Table(name = "specs")
@Builder
@AllArgsConstructor
@NoArgsConstructor
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
    //body
    @Column(name = "ps_sleeve")
    private String sleeve;
    //pants
    @Column(name = "ps_pant_length")
    private Long pantLength;
    //head
    @Column(name = "ps_head_girth")
    private Long headGirth;
    //boots
    @Column(name = "ps_lifting_height")
    private Long liftingHeight;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "spec")
    @JsonManagedReference
    private Product product;
}
