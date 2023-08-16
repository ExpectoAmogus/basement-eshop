package com.eshop.articleservice.controller;

import com.eshop.articleservice.models.dto.ArticleRequest;
import com.eshop.articleservice.models.dto.ArticleResponse;
import com.eshop.articleservice.models.dto.ArticleToUpdateRequest;
import com.eshop.articleservice.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/article")
public class ArticleController {
    private final ArticleService articleService;

    @PostMapping("/add")
    public ResponseEntity<String> createArticle(@RequestBody ArticleRequest articleRequest){
        articleService.create(articleRequest);
        return new ResponseEntity<>("Article created!", HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateArticle(@RequestBody ArticleToUpdateRequest articleRequest){
        articleService.update(articleRequest);
        return new ResponseEntity<>("Article updated!", HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteArticle(@RequestParam Long id){
        articleService.delete(id);
        return new ResponseEntity<>("Article deleted!", HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<ArticleResponse>> getArticles(){
        return new ResponseEntity<>(articleService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ArticleResponse> getArticleById(@PathVariable Long id){
        return new ResponseEntity<>(articleService.findById(id), HttpStatus.OK);
    }
}
