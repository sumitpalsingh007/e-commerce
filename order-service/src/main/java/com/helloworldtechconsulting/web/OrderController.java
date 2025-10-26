package com.helloworldtechconsulting.web;

import com.helloworldtechconsulting.dto.OrderDto;
import com.helloworldtechconsulting.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {

    @Autowired
    OrderService orderService;

    @GetMapping("/{id}")
    public OrderDto getOrderById(@PathVariable Long id) {
        var product = orderService.getOrderById(id);
        return OrderDto.builder()
                .id(id)
                .products(product)
                .build();
    }
}
