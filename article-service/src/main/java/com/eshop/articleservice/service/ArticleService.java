package com.eshop.articleservice.service;

import com.eshop.articleservice.models.entity.Article;

public interface ArticleService<U extends Article> extends CrudService<U> {
}
