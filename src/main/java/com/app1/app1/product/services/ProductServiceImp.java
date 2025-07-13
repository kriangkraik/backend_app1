package com.app1.app1.product.services;

import com.app1.app1.product.exceptions.ProductAlreadyExistException;
import com.app1.app1.product.repositories.ProductRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class ProductServiceImp implements ProductService {

    private final ProductRepository productRepository;

    // Create New Production.
    public Product createProduct(Product product) {
        // Set code if not provided.
        String code = Optional.ofNullable(product.getCode())
                .filter(c -> !c.isEmpty())
                .orElseGet(this::generateUniqueCode);
        product.setCode(code);

        // Validate code uniqueness.
        if (productRepository.existsByCode(code)) {
            throw new ProductAlreadyExistException("Product code already exists: " + code);
        }

        return productRepository.save(product);
    }

    // Find All Product.
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    // Find Product By Id.
    public Product getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Product not found with ID: " + id));
    }

    // Find Product By Code.
    public Product geProductByCode(String code) {
        return productRepository.findByCode(code)
                .orElseThrow(() -> new IllegalArgumentException("Product not found with Code: " + code));
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

    public int updatePriceByCode(BigDecimal price, String code) {
        return productRepository.updatePriceByCode(code, price);
    }

    // Delete Product By ID.
    public void deleteProductById(Long id) {
        productRepository.findById(id)
                .ifPresentOrElse(
                        _ -> productRepository.deleteById(id),
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