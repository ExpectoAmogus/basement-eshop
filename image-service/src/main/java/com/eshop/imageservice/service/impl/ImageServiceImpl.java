package com.eshop.imageservice.service.impl;

import com.eshop.imageservice.service.AmazonS3Service;
import com.eshop.imageservice.service.ImageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ImageServiceImpl implements ImageService {

    private final AmazonS3Service s3Service;

    @Override
    public ResponseEntity<String> getImage(String entityId, String type, Long imageId) {
        List<String> images = s3Service.getImagesByEntityIdAndType(entityId, type);
        if (images.isEmpty() || imageId <= 0 || imageId > images.size()) {
            return ResponseEntity.notFound().build();
        }
        String image = images.get(Math.toIntExact(imageId) - 1);
        if (!image.isEmpty()) {
            return new ResponseEntity<>(image, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.OK);
        }
    }
}
