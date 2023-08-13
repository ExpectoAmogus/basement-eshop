package com.eshop.imageservice.service;

import org.springframework.http.ResponseEntity;

public interface ImageService {
    ResponseEntity<String> getImage(String entityId, String type, Long imageId);
}
