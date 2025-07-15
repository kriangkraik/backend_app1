package com.app1.app1.report.entities;

import java.time.LocalDateTime;

import com.app1.app1.product.entities.Product;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name = "reports")
@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class ProductReport {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message = "DocNo is required.")
	@Column(nullable = false)
	private String docNo;

	private LocalDateTime created;

	private Product products;

}
