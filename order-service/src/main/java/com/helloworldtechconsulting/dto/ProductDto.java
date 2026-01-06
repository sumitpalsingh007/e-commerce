package com.helloworldtechconsulting.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Builder
public class ProductDto implements Serializable {

    Integer id;
    String name;
    String description;
}
