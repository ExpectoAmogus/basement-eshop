package com.eshop.productqueryservice.service.impl;

import com.eshop.productqueryservice.models.dto.ProductEvent;
import com.eshop.productqueryservice.models.entity.Product;
import com.eshop.productqueryservice.models.entity.ProductCategory;
import com.eshop.productqueryservice.models.enums.EventType;
import com.eshop.productqueryservice.repositories.ProductRepository;
import com.eshop.productqueryservice.service.ProductCategoryService;
import com.eshop.productqueryservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
@EnableKafka
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product findById(String id) {
        return productRepository.findById(id)
                .orElseThrow();
    }

    @Override
    public Product findByCode(String code) {
        return productRepository.findByCode(code)
                .orElseThrow();
    }

    @KafkaListener(topics = "product-query-topic", groupId = "productQueryId")
    private void handleProductEvents(ConsumerRecord<String, ProductEvent> record) {
        ProductEvent event = record.value();
        Product product = event.getProduct();
        switch (event.getEventType()){
            case PRODUCT_CREATED -> productRepository.save(product);
            case PRODUCT_UPDATED -> {
                Product existingProduct = productRepository.findByCode(product.getCode())
                        .orElseThrow();
                existingProduct.setName(product.getName());
                existingProduct.setDescription(product.getDescription());
                existingProduct.setSpec(product.getSpec());
                existingProduct.setCategory(product.getCategory());
                existingProduct.setPrice(product.getPrice());
                productRepository.save(existingProduct);
            }
            case PRODUCT_DELETED -> productRepository.delete(product);
        }
    }
}
