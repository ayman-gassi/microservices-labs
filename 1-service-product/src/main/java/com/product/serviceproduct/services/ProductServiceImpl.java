package com.product.serviceproduct.services;

import com.product.serviceproduct.dto.ProductReqDTO;
import com.product.serviceproduct.dto.ProductRespDTO;
import com.product.serviceproduct.entities.Product;
import com.product.serviceproduct.mappers.ProductMapper;
import com.product.serviceproduct.repositories.ProductRepo;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepo productRepo;
    private final ProductMapper productMapper;

    public ProductServiceImpl(ProductRepo productRepo, ProductMapper productMapper) {
        this.productMapper = productMapper;
        this.productRepo = productRepo;
    }

    @Override
    public List<ProductRespDTO> findAll() {
        return productRepo.findAll().stream()
                .map(productMapper::toRespDto)
                .collect(Collectors.toList());
    }

    @Override
    public ProductRespDTO findProductById(Long id) {
        Product product = productRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Product not found"));
        return productMapper.toRespDto(product);
    }

    @Override
    public ProductRespDTO create(ProductReqDTO dto) {
        Product saved = productRepo.save(productMapper.toEntity(dto));
        return productMapper.toRespDto(saved);
    }

    @Override
    public ProductRespDTO update(Long id, ProductReqDTO dto) {
        Product existing = productRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Product not found"));
        // Update fields
        existing.setName(dto.getName());
        existing.setDescription(dto.getDescription());
        existing.setPrix(dto.getPrix());
        existing.setPrixUsine(dto.getPrixUsine());
        existing.setStock(dto.getStock());
        Product saved = productRepo.save(existing);
        return productMapper.toRespDto(saved);
    }

    @Override
    public void delete(Long id) {
        if (!productRepo.existsById(id)) {
            throw new EntityNotFoundException("Product not found");
        }
        productRepo.deleteById(id);
    }
}
