package com.example.tvtcsknow.service;

import com.example.tvtcsknow.model.OrderItem;
import com.example.tvtcsknow.repository.OrderItemRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class OrderItemService {

    private final OrderItemRepository orderItemRepository;

    public List<OrderItem> getAllOrderItems() {
        log.info("Getting all order items");
        return orderItemRepository.findAll();
    }

    public OrderItem getOrderItemById(long id) {
        log.info("Getting order item with id: {}", id);
        return orderItemRepository.findById(id).orElseThrow();
    }

    public OrderItem createOrderItem(OrderItem orderItem) {
        log.info("Creating order item: {}", orderItem);
        return orderItemRepository.save(orderItem);
    }

    public OrderItem updateOrderItem(long id, OrderItem orderItem) {
        log.info("Updating order item with id: {}", id);
        OrderItem orderItemToUpdate = orderItemRepository.findById(id).orElseThrow();
        return orderItemRepository.save(orderItemToUpdate);
    }

    public void deleteOrderItem(long id) {
        log.info("Deleting order item with id: {}", id);
        orderItemRepository.deleteById(id);
    }
}
