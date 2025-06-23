package com.app1.app1;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.app1.app1.product.entity.Product;
import com.app1.app1.product.exceptions.ProductAlreadyExistException;
import com.app1.app1.product.repository.ProductRepository;
import com.app1.app1.product.service.ProductServiceImp;

public class ProductServiceImpTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductServiceImp productServiceImp;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateProduct_WhenCodeIsNull_ShouldGenerateNewCode() {
        Product product = new Product();
        product.setCode("1");
        product.setDescription("Test Description");
        product.setName("Test Product");

        when(productRepository.existsByCode(anyString())).thenReturn(false);
        when(productRepository.save(any(Product.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Product savedProduct = productServiceImp.createProduct(product);

        assertNotNull(savedProduct.getCode());
        assertEquals("Test Product", savedProduct.getName());
        verify(productRepository).save(product);
    }

    @Test
    void testCreateProduct_WhenCodeExists_ShouldThrowException() {
        Product product = new Product();
        product.setCode("DUPLICATE_CODE");
        product.setName("Test Product");

        when(productRepository.existsByCode("DUPLICATE_CODE")).thenReturn(true);

        assertThrows(ProductAlreadyExistException.class, () -> productServiceImp.createProduct(product));
    }

    @Test
    void testCreateProduct_WhenCodeIsGivenAndNotDuplicate_ShouldSave() {
        Product product = new Product();
        product.setCode("UNIQUE_CODE");
        product.setName("ProductX");

        when(productRepository.existsByCode("UNIQUE_CODE")).thenReturn(false);
        when(productRepository.save(any(Product.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Product saved = productServiceImp.createProduct(product);

        assertEquals("UNIQUE_CODE", saved.getCode());
        assertEquals("ProductX", saved.getName());
    }

}
