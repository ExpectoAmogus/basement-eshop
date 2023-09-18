package com.eshop.productcommandservice.service.impl;

import com.eshop.productcommandservice.models.dto.CategoryEvent;
import com.eshop.productcommandservice.models.entity.ProductCategory;
import com.eshop.productcommandservice.models.enums.EventType;
import com.eshop.productcommandservice.repositories.ProductCategoryRepository;
import com.eshop.productcommandservice.service.ProductCategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductCategoryServiceImpl implements ProductCategoryService {
    private final ProductCategoryRepository categoryRepository;
    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Override
    public ProductCategory createCategory(ProductCategory productCategory) {
        String parentId = null;
        if (productCategory.getParentId() != null) {
            ProductCategory parentCategory = categoryRepository.findById(productCategory.getParentId()).orElse(null);
            if (parentCategory != null) {
                parentId = parentCategory.getId();
            }

        }
        ProductCategory category = ProductCategory.builder()
                .name(productCategory.getName())
                .parentId(parentId)
                .build();

        ProductCategory savedCategory = categoryRepository.save(category);
        //send event to query microservice
        kafkaTemplate.send("category-query-topic", new CategoryEvent(
                EventType.CATEGORY_CREATED,
                savedCategory
        ));
        log.info("Category {} is saved", savedCategory.getName());
        return savedCategory;
    }

    @Override
    public void deleteCategory(ProductCategory productCategory) {
        //send event to query microservice
        kafkaTemplate.send("category-query-topic", new CategoryEvent(
                EventType.CATEGORY_DELETED,
                productCategory
        ));
        log.info("Category {} is deleted", productCategory.getName());
        categoryRepository.findAllByParentId(productCategory.getId())
                .ifPresent(categoryRepository::deleteAll);
        categoryRepository.delete(productCategory);
    }

    @Override
    public List<ProductCategory> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public List<ProductCategory> findAllByParentId(String id) {
        return categoryRepository.findAllByParentId(id)
                .orElseThrow();
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
}
