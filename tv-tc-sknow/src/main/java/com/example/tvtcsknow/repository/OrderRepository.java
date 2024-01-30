package com.example.tvtcsknow.repository;

import com.example.tvtcsknow.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> { }
