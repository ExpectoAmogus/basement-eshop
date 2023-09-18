package com.eshop.productqueryservice.models.entity.specsEntitty;

import com.eshop.productqueryservice.models.entity.ProductSpec;
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
