package com.eshop.imageservice.service.impl;

import com.eshop.imageservice.service.AmazonS3Service;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@Setter
@RequiredArgsConstructor
public class AmazonS3ServiceImpl implements AmazonS3Service {

    private final S3Client s3Client;

    @Value("${aws.bucket.name.s3}")
    private String bucketName;

    @Value("${aws.endpoint.url.s3}")
    private String endpointUrl;

    @Override
    public void uploadFile(String keyName, MultipartFile file) {
        try {
            PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                    .bucket(bucketName)
                    .key(keyName)
                    .contentType(file.getContentType())
                    .acl("public-read")
                    .build();
            s3Client.putObject(putObjectRequest, RequestBody.fromInputStream(
                    file.getInputStream(),
                    file.getInputStream().available()));
            log.info("File uploaded to S3 bucket: {}", keyName);
        } catch (S3Exception | IOException e) {
            log.error("Error uploading file to S3 bucket: {}", keyName, e);
        }
    }

    @Override
    public void deleteFile(String keyName) {
        DeleteObjectRequest deleteObjectRequest = DeleteObjectRequest.builder()
                .bucket(bucketName)
                .key(keyName)
                .build();
        s3Client.deleteObject(deleteObjectRequest);
    }

    @Override
    @Cacheable(value = "imageCache", key = "#entityId")
    public List<String> getImagesByEntityIdAndType(String entityId, String type) {
        ListObjectsV2Request listObjectsRequest = ListObjectsV2Request.builder()
                .bucket(bucketName)
                .prefix(type + "/" + entityId + "/")
                .build();
        List<String> keys = new ArrayList<>();
        ListObjectsV2Response listObjectsResponse;
        do {
            listObjectsResponse = s3Client.listObjectsV2(listObjectsRequest);
            for (S3Object s3Object : listObjectsResponse.contents()) {
                keys.add(s3Object.key());
            }
            listObjectsRequest = listObjectsRequest.toBuilder()
                    .continuationToken(listObjectsResponse.nextContinuationToken())
                    .build();
        } while (listObjectsResponse.isTruncated());
        var images = keys.stream().map(key -> endpointUrl + "/" + key).collect(Collectors.toList());
        log.info("Image urls loaded from S3: {}", images);
        return images;
    }
}
