package com.helloworldtechconsulting.service;

import com.helloworldtechconsulting.dto.InventoryEvent;
import com.helloworldtechconsulting.dto.OrderDto;
import com.helloworldtechconsulting.feign.ProductClient;
import com.helloworldtechconsulting.dto.ProductDto;
import com.helloworldtechconsulting.producers.InventoryProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class OrderService {

    @Autowired
    ProductClient productClient;

    @Autowired
    InventoryProducer inventoryProducer;

    public Map<ProductDto, Integer> getOrderById(Long id) {
        return productClient.findByOrderId(id)
                .stream()
                .collect(Collectors.toMap(Function.identity(), ProductDto::getId));
    }

    @CacheEvict(value = "products", key = "#id")
    public void invalidateProductCache(Long id) {
    }

    @CacheEvict(value = "products", allEntries = true)
    public void clearAllOrderCache() {
    }

    @Transactional
    public OrderDto order(long productId, Integer quantity) {
        ProductDto product = productClient.getProductById(productId);
        var order = OrderDto.builder()
                .id(10L)
                .products(Map.of(product, quantity))
                .build();
        inventoryProducer.sendInventoryEvent(new InventoryEvent(productId, quantity));
        return order;
    }
}
