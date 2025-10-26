package com.helloworldtechconsulting.service;

import com.helloworldtechconsulting.dto.ProductDto;
import com.helloworldtechconsulting.feign.ProductClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    ProductClient productClient;

    public List<ProductDto> getOrderById(Long id) {
        return productClient.findByOrderId(id);
    }
}
