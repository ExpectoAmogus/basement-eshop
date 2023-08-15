package com.eshop.productservice.models.entity.specsEntitty;

import com.eshop.productservice.models.entity.ProductSpec;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@Entity
@SuperBuilder
@DiscriminatorValue("HEAD")
public class HeadSpec extends ProductSpec {

    //head
    @Column(name = "ps_head_girth")
    private Long headGirth;

    public HeadSpec(){
        super();
    }
}