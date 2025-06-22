package com.app1.app1.product.service;

import com.app1.app1.product.entity.Product;
import com.app1.app1.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class ProductServiceImp implements ProductService {

    private final ProductRepository productRepository;

    public Product createProduct(Product product) {
        product.setCode(Optional.ofNullable(product.getCode())
                .filter(code -> !code.isEmpty())
                .orElseGet(this::generateUniqueCode));
        return productRepository.save(product);
    }

    // Find All Product.
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    // Find Product By Id
    public Product getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Product not found with ID: " + id));
    }

    // Update Product.
    public Product updateProduct(Long id, Product updatedProduct) {
        Product existing = productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Product not found with ID: " + id));

        existing.setName(updatedProduct.getName());
        existing.setPrice(updatedProduct.getPrice());
        existing.setDescription(updatedProduct.getDescription());
        existing.setCode(updatedProduct.getCode() != null ? updatedProduct.getCode() : existing.getCode());

        return productRepository.save(existing);
    }

    // Delete Product By ID.
    public void deleteProductById(Long id) {
        productRepository.findById(id)
                .ifPresentOrElse(
                        product -> productRepository.deleteById(id),
                        () -> {
                            throw new IllegalArgumentException("Product not found with ID: " + id);
                        });
    }

    // Delete Product By Code.
    public void deleteProductByCode(String code) {
        if (!productRepository.existsByCode(code)) {
            throw new IllegalArgumentException("Product not found with CODE: " + code);
        }
        productRepository.deleteByCode(code);
    }

    private String generateUniqueCode() {
        return Stream.generate(() -> UUID.randomUUID().toString().replace("-", "").substring(0, 12).toUpperCase())
                .filter(code -> !productRepository.existsByCode(code)).findFirst()
                .orElseThrow(() -> new IllegalStateException("Failed to generate unique code"));
    }
}