package com.app1.app1.product.services;

import java.util.List;

import com.app1.app1.product.entity.Product;

public interface ProductService {
    Product createProduct(Product product);

    List<Product> getAllProducts();

    Product getProductById(Long id);

    Product geProductByCode(String code);

    Product updateProduct(Long id, Product updatedProduct);

    void deleteProductById(Long id);

    void deleteProductByCode(String code);
}
