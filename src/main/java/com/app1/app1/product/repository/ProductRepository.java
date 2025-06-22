package com.app1.app1.product.repository;

import com.app1.app1.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
    boolean existsByCode(String code);
    void deleteByCode(String code);
}
