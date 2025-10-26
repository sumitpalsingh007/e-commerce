package com.helloworldtechconsulting.service;

import com.helloworldtechconsulting.entity.Product;
import com.helloworldtechconsulting.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public List<Product> findByOrderId(Long id) {
        var product1 = Product.builder().id(1L).name("p1").build();
        var product2 = Product.builder().id(1L).name("p2").build();
        return List.of(product1, product2);
    }
}
