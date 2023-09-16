package com.eshop.articleservice.factory;

import com.eshop.articleservice.models.dto.ArticleRequest;
import com.eshop.articleservice.models.dto.ArticleToUpdateRequest;
import com.eshop.articleservice.models.entity.BaseArticle;
import com.eshop.articleservice.models.enums.ArticleType;

public interface ArticleFactory {
    BaseArticle createArticle(ArticleRequest articleRequest);
    BaseArticle updateArticle(ArticleToUpdateRequest articleToUpdateRequest, Long id);

    ArticleType getArticleType();
}
