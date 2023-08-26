package com.eshop.articleservice.models.entity;

import com.eshop.articleservice.models.enums.ArticleType;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Table(name = "articles")
public class Article extends BaseEntity {

    @Column(name = "a_title", nullable = false)
    private String title;
    @Column(name = "a_sub_title")
    private String subTitle;
    @Column(name = "a_text", columnDefinition = "text")
    private String text;
    @Enumerated(EnumType.STRING)
    @Column(name = "a_type", nullable = false)
    private ArticleType type;
}
