package com.app1.app1.product.repositories;



import jakarta.transaction.Transactional;

import java.math.BigDecimal;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.app1.app1.product.entities.Product;

@Repository
public interface ProductRepository extends PagingAndSortingProductRepository<Product, Long> {

    // ตรวจสอบว่ามีรหัสสินค้านี้อยู่แล้วหรือไม่
    boolean existsByCode(String code);

    // ลบสินค้าตามรหัส (Transaction should be handled in service layer)
    void deleteByCode(String code);

    // ค้นหาสินค้าจากรหัส
    Optional<Product> findByCode(String code);

    // ค้นหาด้วยชื่อสินค้าแบบบางส่วน(ignorecase)
    Page<Product> findByNameContainingIgnoreCase(String name, Pageable pageable);

    // ค้นหาสินค้าภายใต้ช่วงราคา
    Page<Product> findByPriceBetween(BigDecimal min, BigDecimal max, Pageable pageable);

    // ค้นหาด้วย keyword (JPQL - works with all databases)
    @Query("SELECT p FROM Product p WHERE LOWER(p.name) LIKE LOWER(CONCAT('%', :keyword, '%')) OR LOWER(p.description) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    Page<Product> searchByKeyword(@Param("keyword") String keyword, Pageable pageable);

    // อัปเดตราคาแบบ bulk
    @Transactional
    @Modifying
    @Query("UPDATE Product p SET p.price = :price WHERE p.code = :code")
    int updatePriceByCode(@Param("code") String code, @Param("price") BigDecimal price);

    // Additional useful queries
    @Query("SELECT p FROM Product p WHERE p.price >= :minPrice")
    Page<Product> findByPriceGreaterThanEqual(@Param("minPrice") BigDecimal minPrice, Pageable pageable);

    @Query("SELECT p FROM Product p WHERE p.price <= :maxPrice")
    Page<Product> findByPriceLessThanEqual(@Param("maxPrice") BigDecimal maxPrice, Pageable pageable);
}