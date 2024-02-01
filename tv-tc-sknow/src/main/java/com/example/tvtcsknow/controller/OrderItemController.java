package com.example.tvtcsknow.controller;

import com.example.tvtcsknow.dto.OrderItemCreateRequest;
import com.example.tvtcsknow.model.OrderItem;
import com.example.tvtcsknow.service.OrderItemService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order-items")
@Tag(name = "OrderItem Controller", description = "OrderItem API")
@AllArgsConstructor
public class OrderItemController {

    private final OrderItemService orderItemService;

    @GetMapping("")
    @Operation(summary = "Get all order items")
    public List<OrderItem> getAllOrderItems() {
        return orderItemService.getAllOrderItems();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get order item by id")
    public OrderItem getOrderItemById(@PathVariable Long id) {
        return orderItemService.getOrderItemById(id);
    }

    @PostMapping("")
    @Operation(summary = "Create new order item")
    public OrderItem createOrderItem(@RequestBody OrderItemCreateRequest request) {
        return orderItemService.createOrderItem(request);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update order item by id")
    public OrderItem updateOrderItem(@PathVariable Long id, @RequestBody OrderItem orderItem) {
        return orderItemService.updateOrderItem(id, orderItem);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete order item by id")
    public void deleteOrderItem(@PathVariable Long id) {
        orderItemService.deleteOrderItem(id);
    }

}
