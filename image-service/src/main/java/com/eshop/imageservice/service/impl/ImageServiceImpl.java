package com.eshop.imageservice.service.impl;

import com.eshop.imageservice.models.dto.ImageRequestDto;
import com.eshop.imageservice.service.AmazonS3Service;
import com.eshop.imageservice.service.ImageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class ImageServiceImpl implements ImageService {

    private final AmazonS3Service s3Service;

    @Override
    public ResponseEntity<String> getImage(ImageRequestDto imageRequestDto, Long imageId) {
        List<String> images = s3Service.getImagesByEntityIdAndType(imageRequestDto.entityType(), imageRequestDto.entityId());

        if (images.isEmpty() || imageId <= 0 || imageId > images.size()) {
            return ResponseEntity.notFound().build();
        }

        String image = images.get(Math.toIntExact(imageId) - 1);

        if (image.isEmpty()) {
            return ResponseEntity.ok(null);
        } else {
            return ResponseEntity.ok(image);
        }
    }

    @Override
    public ResponseEntity<List<String>> uploadImage(List<MultipartFile> files, ImageRequestDto imageRequestDto) {
        List<String> newImages = new ArrayList<>();

        if (files == null || files.isEmpty()) {
            return ResponseEntity.ok(newImages);
        }

        for (MultipartFile image : files) {
            String keyName = imageRequestDto.entityType() + "/" +
                    imageRequestDto.entityId() + "/img/" +
                    UUID.randomUUID() + "." + image.getOriginalFilename();

            try {
                s3Service.uploadFile(keyName, image);
                newImages.add(keyName);
            } catch (Exception e) {
                log.error("Error uploading image to S3: {}", keyName, e);
            }
        }

        return ResponseEntity.ok(newImages);
    }

    @Override
    public void deleteImage(ImageRequestDto imageRequestDto, Long imageId) {
        List<String> images = s3Service.getImagesByEntityIdAndType(imageRequestDto.entityType(), imageRequestDto.entityId());

        if (images.isEmpty() || imageId <= 0 || imageId > images.size()) {
            log.warn("Image not found for deletion: entityId={}, entityType={}, imageId={}",
                    imageRequestDto.entityId(), imageRequestDto.entityType(), imageId);
            return;
        }

        String imageKeyToDelete = images.get(Math.toIntExact(imageId) - 1);

        try {
            s3Service.deleteFile(imageKeyToDelete);
            log.info("Image deleted from S3: {}", imageKeyToDelete);
        } catch (Exception e) {
            log.error("Error deleting image from S3: {}", imageKeyToDelete, e);
        }
    }
}
