package com.example.tvtcsknow.service;

import com.example.tvtcsknow.model.Order;
import com.example.tvtcsknow.repository.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@Disabled
@SpringBootTest
public class OrderServiceTest {

    @Mock
    private OrderRepository orderRepository;

    private OrderService orderService;

    @BeforeEach
    public void setUp() {
        orderService = new OrderService(orderRepository);
    }

    @Test
    public void testGetAllOrders() {
        when(orderRepository.findAll()).thenReturn(Arrays.asList(new Order(), new Order()));

        assertEquals(2, orderService.getAllOrders().size());

        verify(orderRepository, times(1)).findAll();
    }

    @Test
    public void testGetOrderById() {
        Order order = new Order();
        when(orderRepository.findById(anyLong())).thenReturn(Optional.of(order));

        assertEquals(order, orderService.getOrderById(1L));

        verify(orderRepository, times(1)).findById(anyLong());
    }

    @Test
    public void testCreateOrder() {
        Order order = new Order();
        when(orderRepository.save(any(Order.class))).thenReturn(order);

        assertEquals(order, orderService.createOrder(new Order()));

        verify(orderRepository, times(1)).save(any(Order.class));
    }

    @Test
    public void testUpdateOrder() {
        Order order = new Order();
        when(orderRepository.findById(anyLong())).thenReturn(Optional.of(new Order()));
        when(orderRepository.save(any(Order.class))).thenReturn(order);

        Order updatedOrder = orderService.updateOrder(1L, order);

        assertEquals(order, updatedOrder);
        verify(orderRepository, times(1)).findById(anyLong());
        verify(orderRepository, times(1)).save(any(Order.class));
    }

    @Test
    public void testDeleteOrder() {
        doNothing().when(orderRepository).deleteById(anyLong());

        orderService.deleteOrder(1L);

        verify(orderRepository, times(1)).deleteById(anyLong());
    }
}