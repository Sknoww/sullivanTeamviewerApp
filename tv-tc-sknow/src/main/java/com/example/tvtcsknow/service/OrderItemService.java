package com.example.tvtcsknow.service;

import com.example.tvtcsknow.dto.OrderItemCreateRequest;
import com.example.tvtcsknow.model.Order;
import com.example.tvtcsknow.model.OrderItem;
import com.example.tvtcsknow.model.Product;
import com.example.tvtcsknow.repository.OrderItemRepository;
import com.example.tvtcsknow.repository.OrderRepository;
import com.example.tvtcsknow.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class OrderItemService {

    private final OrderItemRepository orderItemRepository;

    private final ProductRepository productRepository;

    private final OrderRepository orderRepository;

    public List<OrderItem> getAllOrderItems() {
        log.info("Getting all order items");
        return orderItemRepository.findAll();
    }

    public OrderItem getOrderItemById(long id) {
        log.info("Getting order item with id: {}", id);
        return orderItemRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("OrderItem not found"));
    }

    public OrderItem createOrderItem(OrderItemCreateRequest request) {
        OrderItem orderItem = new OrderItem();
        orderItem.setQuantity(request.getQuantity());

        Order order = orderRepository.findById(request.getOrderId())
                .orElseThrow(() -> new EntityNotFoundException("Order not found with id: " + request.getOrderId()));

        Product product = productRepository.findById(request.getProductId())
                .orElseThrow(() -> new EntityNotFoundException("Product not found with id: " + request.getProductId()));

        orderItem.setOrder(order);
        orderItem.setProduct(product);

        log.info("Creating order item: {}", orderItem);
        return orderItemRepository.save(orderItem);
    }

    public OrderItem updateOrderItem(long id, OrderItem orderItem) {
        log.info("Updating order item with id: {}", id);
        OrderItem orderItemToUpdate = orderItemRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("OrderItem not found"));
        orderItemToUpdate.setQuantity(orderItem.getQuantity());
        return orderItemRepository.save(orderItemToUpdate);
    }

    public void deleteOrderItem(long id) {
        log.info("Deleting order item with id: {}", id);
        orderItemRepository.deleteById(id);
    }
}
