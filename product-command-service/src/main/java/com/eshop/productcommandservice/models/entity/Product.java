package com.eshop.productcommandservice.models.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Document(collection = "products")
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    @Id
    private String id;

    private String code;
    private String name;
    private String description;
    private ProductSpec spec;
    private ProductCategory category;
    private BigDecimal price;

    @CreatedDate
    private LocalDateTime dateOfCreated;
    @LastModifiedDate
    private LocalDateTime dateOfUpdated;
}
