package com.ecommerceMicroservice.productservice.service;

import com.ecommerceMicroservice.productservice.dto.ProductRequest;
import com.ecommerceMicroservice.productservice.dto.ProductResponse;
import com.ecommerceMicroservice.productservice.model.Product;
import com.ecommerceMicroservice.productservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {
    private final ProductRepository productRepository;
    public void createProduct(ProductRequest productRequest){
        Product product = Product.builder()
                .name(productRequest.getName())
                .description(productRequest.getDescription())
                .price(productRequest.getPrice())
                .build();
        productRepository.save(product);
        log.info("Product {} is saved", product.getId());
    }

    public List<ProductResponse> getAllProducts() {
        List<Product> products = productRepository.findAll();

        List<ProductResponse> list = new ArrayList<>();
        for (Product product : products) {
            ProductResponse productResponse = mapToProductResponse(product);
            list.add(productResponse);
        }
        return list;
    }

    private ProductResponse mapToProductResponse(Product product){
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .build();
    }
}
