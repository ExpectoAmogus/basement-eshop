package com.eshop.articleservice.repository;

import com.eshop.articleservice.models.entity.BaseArticle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<BaseArticle, Long> {
}
