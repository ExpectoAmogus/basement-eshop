package com.eshop.articleservice.models.dto;

import com.eshop.articleservice.models.enums.ArticleType;

public record ArticleToUpdateRequest(
        Long id,
        String title,
        String subTitle,
        String text,
        ArticleType type
) {
}
