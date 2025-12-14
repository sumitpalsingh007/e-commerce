package com.helloworldtechconsulting.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@Builder
public class OrderDto {

    Long id;
    Map<ProductDto, Integer> products;
}
