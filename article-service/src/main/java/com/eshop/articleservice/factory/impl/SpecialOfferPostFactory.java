package com.eshop.articleservice.factory.impl;

import com.eshop.articleservice.factory.ArticleFactory;
import com.eshop.articleservice.models.dto.ArticleRequest;
import com.eshop.articleservice.models.dto.ArticleToUpdateRequest;
import com.eshop.articleservice.models.entity.BaseArticle;
import com.eshop.articleservice.models.entity.SpecialOfferPost;
import com.eshop.articleservice.models.enums.ArticleType;
import org.springframework.stereotype.Component;

@Component
public class SpecialOfferPostFactory implements ArticleFactory {
    @Override
    public BaseArticle createArticle(ArticleRequest articleRequest) {
        return SpecialOfferPost.builder()
                .title(articleRequest.title())
                .subTitle(articleRequest.subTitle())
                .text(articleRequest.text())
                .build();
    }

    @Override
    public BaseArticle updateArticle(ArticleToUpdateRequest articleToUpdateRequest, BaseArticle existingArticle) {
        existingArticle.setTitle(articleToUpdateRequest.title());
        existingArticle.setSubTitle(articleToUpdateRequest.subTitle());
        existingArticle.setText(articleToUpdateRequest.text());
        return existingArticle;
    }

    @Override
    public ArticleType getArticleType() {
        return ArticleType.SPECIAL_OFFER_POST;
    }
}
