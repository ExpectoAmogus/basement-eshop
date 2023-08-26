package com.eshop.articleservice.facade;

import com.eshop.articleservice.models.dto.ArticleCreateResponse;
import com.eshop.articleservice.models.dto.ArticleRequest;
import com.eshop.articleservice.models.dto.ArticleResponse;
import com.eshop.articleservice.models.dto.ArticleToUpdateRequest;
import com.eshop.articleservice.models.entity.Article;

import java.util.List;

public interface ArticleFacade {
    ArticleCreateResponse create(ArticleRequest article);
    void update(ArticleToUpdateRequest articleToUpdate);
    void delete(Long id);
    ArticleResponse findById(Long id);
    List<ArticleResponse> findAll();
}
