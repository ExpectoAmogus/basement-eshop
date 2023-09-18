package com.eshop.productcommandservice.models.entity.specsEntitty;

import com.eshop.productcommandservice.models.entity.ProductSpec;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
public class BootsSpec extends ProductSpec {

    //boots
    private Long liftingHeight;
    private String soleType;
    private String clasp;

    public BootsSpec(){
        super();
    }
}
