package com.eshop.articleservice.service;


import com.eshop.articleservice.models.dto.ArticleRequest;
import com.eshop.articleservice.models.dto.ArticleResponse;
import com.eshop.articleservice.models.dto.ArticleToUpdateRequest;

import java.util.List;

public interface ArticleService {
    void create(ArticleRequest articleRequest);
    void update(ArticleToUpdateRequest updateRequest);
    void delete(Long id);
    ArticleResponse findById(Long id);
    List<ArticleResponse> findAll();
}
