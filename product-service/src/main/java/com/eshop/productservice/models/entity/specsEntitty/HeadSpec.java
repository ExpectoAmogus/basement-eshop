package com.eshop.productservice.models.entity.specsEntitty;

import com.eshop.productservice.models.entity.ProductSpec;
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
