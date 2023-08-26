package com.eshop.articleservice.service;


import com.eshop.articleservice.models.dto.ArticleRequest;
import com.eshop.articleservice.models.dto.ArticleResponse;
import com.eshop.articleservice.models.dto.ArticleToUpdateRequest;
import com.eshop.articleservice.models.entity.Article;

import java.util.List;

public interface ArticleService {
    Article create(Article article);
    void update(Article articleToUpdate);
    void delete(Article article);
    Article findById(Long id);
    List<Article> findAll();
}
