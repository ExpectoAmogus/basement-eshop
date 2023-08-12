package com.eshop.productservice.service.impl;

import com.eshop.productservice.models.dto.ProductCategoryDto;
import com.eshop.productservice.models.dto.ProductRequest;
import com.eshop.productservice.models.dto.ProductResponse;
import com.eshop.productservice.models.dto.ProductSpecDto;
import com.eshop.productservice.models.dto.specDtos.BodyDto;
import com.eshop.productservice.models.dto.specDtos.BootsDto;
import com.eshop.productservice.models.dto.specDtos.HeadDto;
import com.eshop.productservice.models.dto.specDtos.PantsDto;
import com.eshop.productservice.models.entity.Product;
import com.eshop.productservice.models.entity.ProductCategory;
import com.eshop.productservice.models.entity.ProductSpec;
import com.eshop.productservice.models.mappers.ProductCategoryMapper;
import com.eshop.productservice.models.mappers.ProductResponseMapper;
import com.eshop.productservice.models.mappers.ProductSpecMapper;
import com.eshop.productservice.repositories.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductCategoryService categoryService;
    private final ProductResponseMapper productResponseMapper;
    private final ProductCategoryMapper productCategoryMapper;
    private final ProductSpecMapper productSpecMapper;

    public void createProduct(ProductRequest productRequest){
        ProductCategory category;
        ProductSpec productSpec;
        if (!categoryExists(productRequest)){
            log.warn("Could not find category {}, creating new one...", productRequest.category().name());
            category = productCategoryMapper.apply(categoryService.createCategory(productRequest.category()));
        }
        else {
            category = productCategoryMapper.apply(categoryService.findByNameAndParentId(productRequest.category().name(),productRequest.category().parentId()));
        }

        ProductSpecDto specDto = productRequest.spec();
        productSpec = productSpecMapper.apply(specDto);

        Product product = Product.builder()
                .name(productRequest.name())
                .code(productRequest.code())
                .description(productRequest.description())
                .spec(productSpec)
                .category(category)
                .price(productRequest.price())
                .build();

        productRepository.save(product);
        log.info("Product {} is created", product.getId());
    }

    public List<ProductResponse> getProducts(){
        return productRepository.findAll()
                .stream()
                .map(productResponseMapper)
                .toList();
    }

    private boolean categoryExists(ProductRequest productRequest) {
        return categoryService.getAllCategories()
                .stream()
                .map(ProductCategoryDto::name)
                .toList()
                .contains(productRequest.category().name());
    }

}
