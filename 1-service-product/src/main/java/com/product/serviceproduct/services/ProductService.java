package com.product.serviceproduct.services;

import com.product.serviceproduct.dto.ProductReqDTO;
import com.product.serviceproduct.dto.ProductRespDTO;

import java.util.List;

public interface ProductService {
    List<ProductRespDTO> findAll();
    ProductRespDTO findProductById(Long id);
    ProductRespDTO create(ProductReqDTO product);
    ProductRespDTO update(Long id, ProductReqDTO product);
    void delete(Long id);
}
