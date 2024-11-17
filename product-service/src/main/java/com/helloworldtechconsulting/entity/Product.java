package com.helloworldtechconsulting.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

@Entity(name = "PRODUCT")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue
    Long id;
    String description;
    String name;
    Double price;
    int orderEstimate;

}
