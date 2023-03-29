package com.productservice.productservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponse {

    private String id;
    private String name;
    private String description;
    private double price;
}
