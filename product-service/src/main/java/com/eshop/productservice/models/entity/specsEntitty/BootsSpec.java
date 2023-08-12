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
@DiscriminatorValue("BOOTS")
public class BootsSpec extends ProductSpec {

    //boots
    @Column(name = "ps_lifting_height")
    private Long liftingHeight;

    public BootsSpec(){
        super();
    }
}
