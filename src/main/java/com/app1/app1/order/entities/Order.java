package com.app1.app1.order.entities;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import com.app1.app1.product.entities.Product;
import com.app1.app1.user.entities.User;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {

	private static final int MAX_PRECISION_PRICE_EACH_LENGTH = 10;
	private static final int MAX_SCALE_PRICE_EACH_LENGTH = 2;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message = "DocNo is required")
	private String docNo;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "product_id", nullable = false)
	private Product product;

	@CreatedDate
	@Column(name = "created_at", nullable = false)
	private LocalDateTime created;

	@LastModifiedDate
	@Column(name = "updated_at", nullable = false)
	private LocalDateTime updated;

	@NotNull(message = "PriceEach is required")
	@DecimalMin(value = "0.00", message = "PriceEach must be non-negative")
	@Column(name = "priceEach", nullable = false, precision = MAX_PRECISION_PRICE_EACH_LENGTH, scale = MAX_SCALE_PRICE_EACH_LENGTH)
	private BigDecimal priceEach;

	private Integer quantity;
}
