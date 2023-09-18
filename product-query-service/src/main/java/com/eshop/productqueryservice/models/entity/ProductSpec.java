package com.eshop.productqueryservice.models.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.Map;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
public class ProductSpec {

    private String type;

    private String size;
    private String color;
    private String sex;
    private String material;
    private String season;
    private String style;
    //extra fields
    private Map<String, Object> extras;

    public ProductSpec(){
        super();
    }
}
