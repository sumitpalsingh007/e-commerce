package com.helloworldtechconsulting.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class OrderDto {

    Long id;
    List<ProductDto> products;
}
