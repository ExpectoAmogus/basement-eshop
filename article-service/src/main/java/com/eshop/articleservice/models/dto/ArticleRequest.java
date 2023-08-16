package com.eshop.articleservice.models.dto;

public record ArticleRequest(
        String title,
        String subTitle,
        String text,
        String type
) {
}
