package com.helloworldtechconsulting.service;

import com.helloworldtechconsulting.feign.ProductClient;
import com.helloworldtechconsulting.dto.ProductDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    ProductClient productClient;

    public List<ProductDto> getOrderById(Long id) {
        return productClient.findByOrderId(id);
    }

    @CacheEvict(value = "products", key = "#id")
    public void invalidateProductCache(Long id) {
    }

    @CacheEvict(value = "products", allEntries = true)
    public void clearAllOrderCache() {
    }
}
