package com.eshop.articleservice.models.entity;

import com.eshop.articleservice.models.enums.ArticleType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@Setter
@AllArgsConstructor
@SuperBuilder
@Table(name = "articles")
public class BaseArticle extends BaseEntity {

    @Column(name = "a_title", nullable = false)
    private String title;
    @Column(name = "a_sub_title")
    private String subTitle;
    @Column(name = "a_text", columnDefinition = "text")
    private String text;
    @Enumerated(EnumType.STRING)
    @Column(name = "a_type", nullable = false)
    private ArticleType type;

    public BaseArticle() {
        super();
    }
}
