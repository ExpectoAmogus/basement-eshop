package com.eshop.articleservice.service.impl;

import com.eshop.articleservice.models.dto.ArticleRequest;
import com.eshop.articleservice.models.dto.ArticleResponse;
import com.eshop.articleservice.models.dto.ArticleToUpdateRequest;
import com.eshop.articleservice.models.entity.Article;
import com.eshop.articleservice.models.mappers.ArticleResponseMapper;
import com.eshop.articleservice.repository.ArticleRepository;
import com.eshop.articleservice.service.ArticleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ArticleServiceImpl implements ArticleService {

    private final ArticleRepository articleRepository;
    private final ArticleResponseMapper articleResponseMapper;
    @Override
    public void create(ArticleRequest articleRequest) {
        Article article = Article.builder()
                .title(articleRequest.title())
                .subTitle(articleRequest.subTitle())
                .text(articleRequest.text())
                .type(articleRequest.type())
                .build();

        articleRepository.save(article);
        log.info("Article {} is successfully saved", article.getId());
    }

    @Override
    public void update(ArticleToUpdateRequest updateRequest) {
        Article articleToUpdate = articleRepository.findById(updateRequest.id()).orElse(null);
        if (articleToUpdate == null){
            log.error("Article not found!");
            return;
        }
        articleToUpdate = Article.builder()
                .title(updateRequest.title())
                .subTitle(updateRequest.subTitle())
                .text(updateRequest.text())
                .type(updateRequest.type())
                .build();

        articleRepository.save(articleToUpdate);
        log.info("Article {} is successfully updated", articleToUpdate.getId());
    }

    @Override
    public void delete(Long id) {
        Article articleToDelete = articleRepository.findById(id).orElse(null);
        if (articleToDelete == null){
            log.error("Article not found!");
            return;
        }
        articleRepository.delete(articleToDelete);
        log.info("Article {} is successfully deleted", id);
    }

    @Override
    public ArticleResponse findById(Long id) {
        return articleRepository.findById(id)
                .map(articleResponseMapper)
                .orElse(null); // TODO: поменять
    }

    @Override
    public List<ArticleResponse> findAll() {
        return articleRepository.findAll()
                .stream()
                .map(articleResponseMapper)
                .toList();
    }
}
