package com.eshop.productcommandservice.facade.impl;

import com.eshop.productcommandservice.facade.ProductFacade;
import com.eshop.productcommandservice.models.dto.*;
import com.eshop.productcommandservice.models.entity.Product;
import com.eshop.productcommandservice.models.enums.EventType;
import com.eshop.productcommandservice.models.mappers.ProductCategoryMapper;
import com.eshop.productcommandservice.models.mappers.ProductSpecMapper;
import com.eshop.productcommandservice.service.ProductService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;


@Service
@Slf4j
@RequiredArgsConstructor
public class ProductFacadeImpl implements ProductFacade {
    private final ProductService productService;
    private final ProductSpecMapper productSpecMapper;
    private final ProductCategoryMapper productCategoryMapper;
    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Override
    public ProductCreateResponse createProduct(ProductRequest productRequest, HttpServletRequest request) {
        Product product = Product.builder()
                .name(productRequest.name())
                .code(productRequest.code())
                .description(productRequest.description())
                .category(productCategoryMapper.apply(productRequest.category()))
                .spec(productSpecMapper.apply(productRequest.spec()))
                .price(productRequest.price())
                .build();
        Product createdProduct = productService.createProduct(product);

        kafkaTemplate.send("product-topic", new InventoryRequest(
                EventType.PRODUCT_CREATED,
                productRequest.code(),
                productRequest.quantity()
        ));

        return new ProductCreateResponse(createdProduct.getId(), createdProduct.getCode());
    }

    @Override
    public void updateProduct(ProductToUpdateRequest updateRequest, HttpServletRequest request) {
        try {
            Product existingProduct = productService.findByCode(updateRequest.code());
            existingProduct.setName(updateRequest.name());
            existingProduct.setDescription(updateRequest.description());
            existingProduct.setSpec(productSpecMapper.apply(updateRequest.spec()));
            existingProduct.setCategory(productCategoryMapper.apply(updateRequest.category()));
            existingProduct.setPrice(updateRequest.price());

            productService.updateProduct(existingProduct);

            kafkaTemplate.send("product-topic", new InventoryRequest(
                    EventType.PRODUCT_UPDATED,
                    updateRequest.code(),
                    updateRequest.quantity()
            ));

        } catch (Exception e) {
            log.error("Product does not exist!");
        }
    }

    @Override
    public void deleteProduct(String code) {
        try {
            Product product = productService.findByCode(code);
            productService.deleteProduct(product);

            kafkaTemplate.send("product-topic", new InventoryRequest(
                    EventType.PRODUCT_DELETED,
                    code,
                    0
            ));
        } catch (Exception e) {
            log.error("Product does not exist!");
        }
    }
}
