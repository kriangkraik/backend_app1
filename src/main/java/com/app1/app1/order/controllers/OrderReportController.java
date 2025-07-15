package com.app1.app1.order.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app1.app1.order.entities.Order;
import com.app1.app1.order.services.OrderService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/order")
@RequiredArgsConstructor
public class OrderReportController {
	private static final Logger LOGGER = LoggerFactory.getLogger(OrderReportController.class);

	private final OrderService orderService;

	@GetMapping("/all")
	public ResponseEntity<List<Order>> getOrders() {
		LOGGER.info("getOrders endpoint complete.");
		return ResponseEntity.ok(orderService.getAllOrder());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Order> getOrderById(@PathVariable Long id) {
		LOGGER.info("Accessed /{} getOrderById endpoint complete.", id);
		return orderService.getOrderById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
	}

}
