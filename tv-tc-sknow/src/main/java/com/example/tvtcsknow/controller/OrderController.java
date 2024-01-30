package com.example.tvtcsknow.controller;

import com.example.tvtcsknow.model.Order;
import com.example.tvtcsknow.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@Tag(name = "Order Controller", description = "Order API")
@AllArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @GetMapping("")
    @Operation(summary = "Get all orders")
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get order by id")
    public Order getOrderById(@PathVariable Long id) {
        return orderService.getOrderById(id);
    }

    @PostMapping("")
    @Operation(summary = "Create new order")
    public Order createOrder(@RequestBody Order order) {
        return orderService.createOrder(order);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update order by id")
    public Order updateOrder(@PathVariable Long id, @RequestBody Order order) {
        return orderService.updateOrder(id, order);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete order by id")
    public void deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);
    }


}
