package com.helloworldtechconsulting.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ProductDto {

    String id;
    String name;
    String description;
}
