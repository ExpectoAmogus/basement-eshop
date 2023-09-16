package com.eshop.articleservice.service;


import com.eshop.articleservice.models.entity.BaseArticle;

import java.util.List;

public interface ArticleService {
    BaseArticle create(BaseArticle article);
    void update(BaseArticle articleToUpdate);
    void delete(BaseArticle article);
    BaseArticle findById(Long id);
    List<BaseArticle> findAll();
}
