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
@DiscriminatorValue("BODY")
public class BodySpec extends ProductSpec {

    //body
    @Column(name = "ps_sleeve")
    private String sleeve;

    public BodySpec(){
        super();
    }
}
