package com.product.serviceproduct.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductReqDTO {
    private String name;
    private String description;
    private double prix;
    private double prixUsine;
    private Integer stock;
}
