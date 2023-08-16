package com.eshop.articleservice.models.dto;

import com.eshop.articleservice.models.enums.ArticleType;

import java.time.LocalDateTime;

public record ArticleResponse(
        Long id,
        LocalDateTime createdAt,
        String title,
        String subTitle,
        String text,
        ArticleType type
) {
}
