package com.eshop.articleservice.models.mappers;

import com.eshop.articleservice.models.dto.ArticleResponse;
import com.eshop.articleservice.models.entity.BaseArticle;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class ArticleResponseMapper implements Function<BaseArticle, ArticleResponse> {
    @Override
    public ArticleResponse apply(BaseArticle article) {
        return new ArticleResponse(
                article.getId(),
                article.getDateOfCreated(),
                article.getTitle(),
                article.getSubTitle(),
                article.getText(),
                article.getType()
        );
    }
}
