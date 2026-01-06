package com.helloworldtechconsulting.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class InventoryEvent {
    private Long productId;
    private Integer quantity;
}