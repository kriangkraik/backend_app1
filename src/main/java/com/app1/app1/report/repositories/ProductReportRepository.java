package com.app1.app1.report.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app1.app1.product.entities.Product;

public interface ProductReportRepository extends JpaRepository<Product, Long> {

}
