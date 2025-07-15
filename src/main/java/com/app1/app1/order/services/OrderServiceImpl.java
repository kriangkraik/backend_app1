package com.app1.app1.order.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.app1.app1.order.entities.Order;
import com.app1.app1.order.repositories.OrderRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

	private OrderRepository orderRepository;

	public List<Order> getAllOrder() {
		return orderRepository.findAll();
	}

	public Optional<Order> getOrderById(Long id) {
		return orderRepository.findById(id);
	}

	public void deleteById(Long id) {
		orderRepository.deleteById(id);
	}

}
