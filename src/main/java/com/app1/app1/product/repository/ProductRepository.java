package com.app1.app1.product.repository;

import com.app1.app1.product.entity.Product;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProductRepository extends JpaRepository<Product, Long> {
    // ตรวจสอบว่ามีรหัสสินค้านี้อยู่แล้วหรือไม่
    boolean existsByCode(String code);

    // ลบสินค้าตามรหัส
    void deleteByCode(String code);

    // ค้นหาสินค้าจากรหัส (ใช้สำหรับกรณีการแสดงผล)
    Optional<Product> findByCode(String code);

    // ค้นหาด้วยชื่อสินค้าแบบบางส่วน(ignorecase)
    Page<Product> findByNameContainingIgnoreCase(String name, Pageable pageable);

    // ค้นหาสินค้าภายใต้ช่วงราคา
    Page<Product> findByPriceBetween(BigDecimal min, BigDecimal max, Pageable pageable);

    @Query("SELECT p FROM Product p WHERE LOWER(p.name) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    Page<Product> searchByKeyword(@Param("keyword") String keyword, Pageable pageable);

    // ใช้ Native SQL (บางระบบต้องใช้ เช่น Full-Text Search)
    @Query(value = "SELECT * FROM products WHERE MATCH(name, description) AGAINST (:keyword IN NATURAL LANGUAGE MODE)", nativeQuery = true)
    Page<Product> fullTextSearch(@Param("keyword") String keyword, Pageable pageable);

    // อัปเดตราคาแบบ bulk (ตัวอย่างการใช้ @Modifying)
    @Modifying
    @Query("UPDATE Product p SET p.price = :price WHERE p.code = :code")
    int updatePriceByCode(@Param("price") BigDecimal price, @Param("code") String code);

}
