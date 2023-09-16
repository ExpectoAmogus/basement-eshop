package com.eshop.articleservice.factory.impl;

import com.eshop.articleservice.factory.ArticleFactory;
import com.eshop.articleservice.models.dto.ArticleRequest;
import com.eshop.articleservice.models.dto.ArticleToUpdateRequest;
import com.eshop.articleservice.models.entity.BaseArticle;
import com.eshop.articleservice.models.entity.NewsPost;
import com.eshop.articleservice.models.enums.ArticleType;
import org.springframework.stereotype.Component;

@Component
public class NewsPostFactory implements ArticleFactory {
    @Override
    public BaseArticle createArticle(ArticleRequest articleRequest) {
        return NewsPost.builder()
                .title(articleRequest.title())
                .subTitle(articleRequest.subTitle())
                .text(articleRequest.text())
                .build();
    }

    @Override
    public BaseArticle updateArticle(ArticleToUpdateRequest articleToUpdateRequest, Long id) {
        return NewsPost.builder()
                .id(id)
                .title(articleToUpdateRequest.title())
                .subTitle(articleToUpdateRequest.subTitle())
                .text(articleToUpdateRequest.text())
                .build();
    }

    @Override
    public ArticleType getArticleType() {
        return ArticleType.NEWS_POST;
    }
}
