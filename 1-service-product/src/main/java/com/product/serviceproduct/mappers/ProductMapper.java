package com.product.serviceproduct.mappers;

import com.product.serviceproduct.dto.ProductReqDTO;
import com.product.serviceproduct.dto.ProductRespDTO;
import com.product.serviceproduct.entities.Product;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProductMapper {
    Product toEntity(ProductReqDTO dto);
    ProductRespDTO toRespDto(Product entity);
}
