package com.eshop.articleservice.models.entity;

import com.eshop.articleservice.models.enums.ArticleType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@Setter
@SuperBuilder
@DiscriminatorValue("NEWS_POST")
public class NewsPost extends BaseArticle {
    public NewsPost() {
        super();
        setType(ArticleType.NEWS_POST);
    }
}
