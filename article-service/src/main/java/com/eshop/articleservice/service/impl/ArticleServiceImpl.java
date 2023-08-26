package com.eshop.articleservice.service.impl;

import com.eshop.articleservice.models.entity.Article;
import com.eshop.articleservice.repository.ArticleRepository;
import com.eshop.articleservice.service.ArticleService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ArticleServiceImpl implements ArticleService {

    private final ArticleRepository articleRepository;

    @Override
    public Article create(Article article) {
        article = Article.builder()
                .title(article.getTitle())
                .subTitle(article.getSubTitle())
                .text(article.getText())
                .type(article.getType())
                .build();

        Article newArticle = articleRepository.save(article);
        log.info("Article {} is successfully saved", newArticle.getId());
        return newArticle;

    }

    @Override
    public void update(Article articleToUpdate) {
        articleToUpdate = Article.builder()
                .id(articleToUpdate.getId())
                .title(articleToUpdate.getTitle())
                .subTitle(articleToUpdate.getSubTitle())
                .text(articleToUpdate.getText())
                .type(articleToUpdate.getType())
                .build();

        articleRepository.save(articleToUpdate);
        log.info("Article {} is successfully updated", articleToUpdate.getId());
    }

    @Override
    public void delete(Article article) {
        articleRepository.delete(article);
        log.info("Article {} is successfully deleted", article.getId());
    }

    @Override
    public Article findById(Long id) {
        return articleRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public List<Article> findAll() {
        return articleRepository.findAll();
    }
}
