package com.app1.app1.service;

import com.app1.app1.entities.Product;
import com.app1.app1.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductServiceImp implements ProductService {

    private final ProductRepository productRepository;

    public Product createProduct(Product product) {
        if (product.getCode() == null || product.getCode().isEmpty()) {
            product.setCode(generateUniqueCode());
        }
        return productRepository.save(product);
    }

    private String generateUniqueCode() {
        String code;
        do {
            code = UUID.randomUUID().toString().replace("-", "").substring(0, 12).toUpperCase();
        } while (productRepository.existsByCode(code)); // ตรวจสอบไม่ให้ซ้ำ

        return code;
    }
}
