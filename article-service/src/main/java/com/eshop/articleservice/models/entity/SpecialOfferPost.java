package com.eshop.articleservice.models.entity;

import com.eshop.articleservice.models.enums.ArticleType;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.Map;

@Entity
@Getter
@Setter
@SuperBuilder
@DiscriminatorValue("SPECIAL_OFFER_POST")
public class SpecialOfferPost extends BaseArticle{

    private Map<String, Object> banner;
    public SpecialOfferPost() {
        super();
        setType(ArticleType.SPECIAL_OFFER_POST);
    }
}
