package com.eshop.articleservice.facade.impl;

import com.eshop.articleservice.facade.ArticleFacade;
import com.eshop.articleservice.models.dto.ArticleCreateResponse;
import com.eshop.articleservice.models.dto.ArticleRequest;
import com.eshop.articleservice.models.dto.ArticleResponse;
import com.eshop.articleservice.models.dto.ArticleToUpdateRequest;
import com.eshop.articleservice.models.entity.Article;
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

    @Override
    public ArticleCreateResponse create(ArticleRequest articleRequest) {
        Article article = Article.builder()
                .title(articleRequest.title())
                .subTitle(articleRequest.subTitle())
                .text(articleRequest.text())
                .type(articleRequest.type())
                .build();
        Article newArticle = articleService.create(article);
        return new ArticleCreateResponse(newArticle.getId(), newArticle.getType().toString());
    }

    @Override
    public void update(ArticleToUpdateRequest articleToUpdate) {
        try{
            Article existingArticle = articleService.findById(articleToUpdate.id());

            Article article = Article.builder()
                    .id(existingArticle.getId())
                    .title(articleToUpdate.title())
                    .subTitle(articleToUpdate.subTitle())
                    .text(articleToUpdate.text())
                    .type(articleToUpdate.type())
                    .build();

            articleService.update(article);
        }
        catch (EntityNotFoundException e){
            log.error("Article does not exist!");
        }

    }

    @Override
    public void delete(Long id) {
        try {
            Article articleToDelete = articleService.findById(id);

            articleService.delete(articleToDelete);
        }
        catch (EntityNotFoundException e){
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
