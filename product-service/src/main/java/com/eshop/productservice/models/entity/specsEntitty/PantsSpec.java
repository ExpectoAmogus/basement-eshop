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
@DiscriminatorValue("PANTS")
public class PantsSpec extends ProductSpec {

    //pants
    @Column(name = "ps_pant_length")
    private Long pantLength;

    public PantsSpec(){
        super();
    }
}
