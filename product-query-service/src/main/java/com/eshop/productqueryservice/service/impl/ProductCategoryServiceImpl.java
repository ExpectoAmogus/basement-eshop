package com.eshop.productqueryservice.service.impl;

import com.eshop.productqueryservice.models.dto.CategoryEvent;
import com.eshop.productqueryservice.models.entity.ProductCategory;
import com.eshop.productqueryservice.repositories.ProductCategoryRepository;
import com.eshop.productqueryservice.service.ProductCategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
@EnableKafka
public class ProductCategoryServiceImpl implements ProductCategoryService {
    private final ProductCategoryRepository categoryRepository;

    @Override
    public List<ProductCategory> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public ProductCategory findById(String id) {
        return categoryRepository.findById(id)
                .orElseThrow();
    }

    @Override
    public ProductCategory findByNameAndParentId(String name, String parentId) {
        return categoryRepository.findByNameAndParentId(name, parentId)
                .orElseThrow();
    }

    @Override
    public List<ProductCategory> findAllByParentId(String parentId) {
        return categoryRepository.findAllByParentId(parentId)
                .orElseThrow();
    }

    @KafkaListener(topics = "category-query-topic", groupId = "productQueryId")
    private void handleCategoryEvents(ConsumerRecord<String, CategoryEvent> record) {
        var event = record.value();
        var category = event.getCategory();
        switch (event.getEventType()) {
            case CATEGORY_CREATED -> categoryRepository.save(category);
            case CATEGORY_DELETED -> {
                categoryRepository.findAllByParentId(category.getId())
                        .ifPresent(categoryRepository::deleteAll);
                categoryRepository.delete(category);
            }
        }
    }
}
