package com.eshop.imageservice.controllers;

import com.eshop.imageservice.models.dto.ImageRequestDto;
import com.eshop.imageservice.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/images")
public class ImageController {

    private final ImageService imageService;

    @GetMapping("/{type}/{entityId}/{imageId}")
    public ResponseEntity<String> getImage(
            @PathVariable String type,
            @PathVariable Long entityId,
            @PathVariable Long imageId) {
        ImageRequestDto imageRequestDto = new ImageRequestDto(type, entityId);
        return imageService.getImage(imageRequestDto, imageId);
    }

    @PostMapping("/{type}/{entityId}")
    public ResponseEntity<List<String>> uploadImage(
            @PathVariable String type,
            @PathVariable Long entityId,
            @RequestParam List<MultipartFile> files) {
        ImageRequestDto imageRequestDto = new ImageRequestDto(type, entityId);
        return imageService.uploadImage(files, imageRequestDto);
    }

    @DeleteMapping("/{type}/{entityId}/{imageId}")
    public ResponseEntity<String> deleteImage(
            @PathVariable String type,
            @PathVariable Long entityId,
            @PathVariable Long imageId) {
        ImageRequestDto imageRequestDto = new ImageRequestDto(type, entityId);
        imageService.deleteImage(imageRequestDto, imageId);
        return ResponseEntity.ok("Image deleted successfully");
    }
}
