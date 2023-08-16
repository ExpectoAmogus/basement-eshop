package com.eshop.articleservice.service;

import com.eshop.articleservice.models.entity.Article;

public interface CrudService<AE extends Article> {
    void create(AE ae);
    void update(AE ae);
    void delete(Long id);
    AE findById(Long id);
}
