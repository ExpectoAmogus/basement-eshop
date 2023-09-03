package com.eshop.imageservice.controllers;

import com.eshop.imageservice.models.dto.ImageRequestDto;
import com.eshop.imageservice.service.ImageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/image")
public class ImageController {

    private final ImageService imageService;

    @GetMapping("/{type}/{entityId}/{imageId}")
    public ResponseEntity<String> getImage(
            @PathVariable String type,
            @PathVariable Long entityId,
            @PathVariable Long imageId) {
        ImageRequestDto imageRequestDto = new ImageRequestDto(type, entityId);
        log.info("Requesting image");
        return imageService.getImage(imageRequestDto, imageId);
    }

    @PostMapping("/{type}/{entityId}")
    public ResponseEntity<List<String>> uploadImage(
            @PathVariable String type,
            @PathVariable Long entityId,
            @RequestParam(name = "images") List<MultipartFile> files) {
        ImageRequestDto imageRequestDto = new ImageRequestDto(type, entityId);
        log.info("Uploading image");
        return imageService.uploadImage(files, imageRequestDto);
    }

    @DeleteMapping("/{type}/{entityId}/{imageId}")
    public ResponseEntity<String> deleteImage(
            @PathVariable String type,
            @PathVariable Long entityId,
            @PathVariable Long imageId) {
        ImageRequestDto imageRequestDto = new ImageRequestDto(type, entityId);
        log.info("Deleting image");
        imageService.deleteImage(imageRequestDto, imageId);
        return ResponseEntity.ok("Image deleted successfully");
    }
}
