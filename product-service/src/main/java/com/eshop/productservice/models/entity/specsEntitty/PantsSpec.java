package com.eshop.productservice.models.entity.specsEntitty;

import com.eshop.productservice.models.entity.ProductSpec;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
public class PantsSpec extends ProductSpec {

    //pants
    private Long pantLength;

    public PantsSpec(){
        super();
    }
}
