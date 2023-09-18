package com.eshop.productqueryservice.models.entity.specsEntitty;

import com.eshop.productqueryservice.models.entity.ProductSpec;
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
