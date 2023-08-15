package com.eshop.imageservice.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface AmazonS3Service {
    void uploadFile(String keyName, MultipartFile file);
    void deleteFile(String keyName);
    List<String> getImagesByEntityIdAndType(String type, Long entityId);
}
