package com.eshop.articleservice.service.impl;

import com.eshop.articleservice.models.entity.Article;
import com.eshop.articleservice.service.ArticleService;
import org.springframework.stereotype.Service;

@Service
public class ArticleServiceImpl implements ArticleService<Article> {
    @Override
    public void create(Article article) {

    }

    @Override
    public void update(Article article) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public Article findById(Long id) {
        return null;
    }
}
