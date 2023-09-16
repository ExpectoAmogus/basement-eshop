package com.eshop.articleservice.service.impl;

import com.eshop.articleservice.models.entity.BaseArticle;
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
    public BaseArticle create(BaseArticle article) {
        BaseArticle newArticle = articleRepository.save(article);
        log.info("Article {} is successfully saved", newArticle.getId());
        return newArticle;

    }

    @Override
    public void update(BaseArticle articleToUpdate) {
        articleRepository.save(articleToUpdate);
        log.info("Article {} is successfully updated", articleToUpdate.getId());
    }

    @Override
    public void delete(BaseArticle article) {
        articleRepository.delete(article);
        log.info("Article {} is successfully deleted", article.getId());
    }

    @Override
    public BaseArticle findById(Long id) {
        return articleRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public List<BaseArticle> findAll() {
        return articleRepository.findAll();
    }
}
