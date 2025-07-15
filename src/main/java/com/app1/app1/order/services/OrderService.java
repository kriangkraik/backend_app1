package com.app1.app1.order.services;

import java.util.List;
import java.util.Optional;

import com.app1.app1.order.entities.Order;

public interface OrderService {
	List<Order> getAllOrder();

	Optional<Order> getOrderById(Long id);

	void deleteById(Long id);
}
