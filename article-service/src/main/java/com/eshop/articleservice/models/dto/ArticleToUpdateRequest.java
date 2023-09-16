package com.eshop.articleservice.models.dto;

public record ArticleToUpdateRequest(
        Long id,
        String title,
        String subTitle,
        String text,
        String type
) {
}
