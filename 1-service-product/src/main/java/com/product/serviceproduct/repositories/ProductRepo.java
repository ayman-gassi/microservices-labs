package com.product.serviceproduct.repositories;

import com.product.serviceproduct.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepo extends JpaRepository<Product,Long> {
}
