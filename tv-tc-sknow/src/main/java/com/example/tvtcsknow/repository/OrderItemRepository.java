package com.example.tvtcsknow.repository;

import com.example.tvtcsknow.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> { }
