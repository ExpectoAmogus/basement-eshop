package com.eshop.articleservice.models.entity;

import com.eshop.articleservice.models.enums.ArticleType;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@Setter
@SuperBuilder
@DiscriminatorValue("BLOG_POST")
public class BlogPost extends BaseArticle {
    public BlogPost() {
        super();
        setType(ArticleType.BLOG_POST);
    }
}
