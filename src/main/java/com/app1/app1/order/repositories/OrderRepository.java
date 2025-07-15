package com.app1.app1.order.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app1.app1.order.entities.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
