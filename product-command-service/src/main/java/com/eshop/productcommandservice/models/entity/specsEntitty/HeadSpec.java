package com.eshop.productcommandservice.models.entity.specsEntitty;

import com.eshop.productcommandservice.models.entity.ProductSpec;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
public class HeadSpec extends ProductSpec {

    //head
    private Long headGirth;

    public HeadSpec(){
        super();
    }
}
