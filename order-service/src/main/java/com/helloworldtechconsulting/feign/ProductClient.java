package com.helloworldtechconsulting.feign;

import com.helloworldtechconsulting.config.OrderServiceFeignConfig;
import com.helloworldtechconsulting.dto.ProductDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "product-client", url = "http://localhost:8080", configuration = OrderServiceFeignConfig.class)
public interface ProductClient {

    @GetMapping("/product/byOrderId/{id}")
    List<ProductDto> findByOrderId(@PathVariable Long id);
}
