package com.example.tvtcsknow.service;

import com.example.tvtcsknow.model.Order;
import com.example.tvtcsknow.repository.OrderRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    public List<Order> getAllOrders() {
        log.info("Getting all orders");
        return orderRepository.findAll();
    }

    public Order getOrderById(long id) {
        log.info("Getting order with id: {}", id);
        return orderRepository.findById(id).orElseThrow();
    }

    public Order createOrder(Order order) {
        log.info("Creating order: {}", order);
        return orderRepository.save(order);
    }

    public Order updateOrder(long id, Order order) {
        log.info("Updating order with id: {}", id);
        Order orderToUpdate = orderRepository.findById(id).orElseThrow();
        return orderRepository.save(orderToUpdate);
    }

    public void deleteOrder(long id) {
        log.info("Deleting order with id: {}", id);
        orderRepository.deleteById(id);
    }
}
