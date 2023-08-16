package com.eshop.articleservice.models.mappers;

import com.eshop.articleservice.models.dto.ArticleResponse;
import com.eshop.articleservice.models.entity.Article;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class ArticleResponseMapper implements Function<Article, ArticleResponse> {
    @Override
    public ArticleResponse apply(Article article) {
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
