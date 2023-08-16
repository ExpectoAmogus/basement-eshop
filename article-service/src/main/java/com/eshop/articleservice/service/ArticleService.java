package com.eshop.articleservice.service;


import com.eshop.articleservice.models.dto.ArticleRequest;
import com.eshop.articleservice.models.dto.ArticleResponse;

public interface ArticleService {
    void create(ArticleRequest articleRequest);
    void update(ArticleRequest articleRequest);
    void delete(Long id);
    ArticleResponse findById(Long id);
}
