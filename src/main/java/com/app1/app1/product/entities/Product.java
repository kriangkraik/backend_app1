package com.app1.app1.product.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "products")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    private static final int MAX_CODE_LENGTH = 12;
    private static final int MAX_NAME_LENGTH = 50;
    private static final int MAX_DESCRIPTION_LENGTH = 100;
    private static final int MAX_PRECISION_PRICE_LENGTH = 10;
    private static final int MAX_SCALE_PRICE_LENGTH = 2;

    @Version
    private Integer version;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 12, message = "Code must not exceed 12 characters")
    @Column(name = "code", nullable = false, unique = true, length = MAX_CODE_LENGTH)
    private String code;

    @NotBlank(message = "Name is required")
    @Size(max = 50, message = "Name must not exceed 50 characters")
    @Column(name = "name", nullable = false, length = MAX_NAME_LENGTH)
    private String name;

    @NotBlank(message = "Description is required")
    @Size(max = 100, message = "Description must not exceed 100 characters")
    @Column(name = "description", nullable = false, length = MAX_DESCRIPTION_LENGTH)
    private String description;

    @NotNull(message = "Price is required")
    @DecimalMin(value = "0.00", message = "Price must be non-negative")
    @Column(name = "price", nullable = false, precision = MAX_PRECISION_PRICE_LENGTH, scale = MAX_SCALE_PRICE_LENGTH)
    private BigDecimal price;
}
