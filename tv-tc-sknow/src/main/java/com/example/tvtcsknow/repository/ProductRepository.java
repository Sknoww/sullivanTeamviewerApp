package com.example.tvtcsknow.repository;

import com.example.tvtcsknow.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> { }
