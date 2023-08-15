package com.eshop.imageservice.service;

import com.eshop.imageservice.models.dto.ImageRequestDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ImageService {
    ResponseEntity<String> getImage(ImageRequestDto imageRequestDto, Long imageId);
    ResponseEntity<List<String>> uploadImage(List<MultipartFile> files, ImageRequestDto imageRequestDto);
    void deleteImage(ImageRequestDto imageRequestDto, Long imageId);
}
