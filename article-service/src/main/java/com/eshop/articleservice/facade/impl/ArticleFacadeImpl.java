package com.eshop.articleservice.facade.impl;

import com.eshop.articleservice.facade.ArticleFacade;
import com.eshop.articleservice.factory.ArticleFactory;
import com.eshop.articleservice.models.dto.ArticleCreateResponse;
import com.eshop.articleservice.models.dto.ArticleRequest;
import com.eshop.articleservice.models.dto.ArticleResponse;
import com.eshop.articleservice.models.dto.ArticleToUpdateRequest;
import com.eshop.articleservice.models.entity.BaseArticle;
import com.eshop.articleservice.models.mappers.ArticleResponseMapper;
import com.eshop.articleservice.service.ArticleService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ArticleFacadeImpl implements ArticleFacade {
    private final ArticleService articleService;
    private final ArticleResponseMapper articleResponseMapper;
    private final List<ArticleFactory> articleFactories;

    @Override
    public ArticleCreateResponse create(ArticleRequest articleRequest) {
        for (ArticleFactory factory : articleFactories) {
            if (articleRequest.type().equals(String.valueOf(factory.getArticleType()))) {
                var newArticle = articleService.create(factory.createArticle(articleRequest));
                return new ArticleCreateResponse(newArticle.getId(), newArticle.getType().toString());
            }
        }
        throw new IllegalArgumentException("Invalid article type: " + articleRequest.type());
    }

    @Override
    public void update(ArticleToUpdateRequest articleToUpdate) {
        try {
            BaseArticle existingArticle = articleService.findById(articleToUpdate.id());
            for (ArticleFactory factory : articleFactories) {
                if (articleToUpdate.type().equals(String.valueOf(factory.getArticleType()))) {
                    articleService.update(factory.updateArticle(articleToUpdate, existingArticle.getId()));
                }
            }
        } catch (EntityNotFoundException e) {
            log.error("Article does not exist!");
        }
    }

    @Override
    public void delete(Long id) {
        try {
            articleService.delete(articleService.findById(id));
        } catch (EntityNotFoundException e) {
            log.error("Article does not exist!");
        }

    }

    @Override
    public ArticleResponse findById(Long id) {
        return articleResponseMapper.apply(articleService.findById(id));
    }

    @Override
    public List<ArticleResponse> findAll() {
        return articleService.findAll().stream().map(articleResponseMapper).toList();
    }
}
