package com.eshop.articleservice.repository;

import com.eshop.articleservice.models.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, Long> {
}
