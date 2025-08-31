package com.helloworldtechconsulting.service;

import com.helloworldtechconsulting.entity.Product;
import com.helloworldtechconsulting.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    public Long save(Product product) {
        Product saved = productRepository.save(product);
        return saved.getId();
    }

    public Product searchByProductName(String productName) {
        return productRepository.findByName(productName);
    }
}
