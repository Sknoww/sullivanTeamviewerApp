package com.example.tvtcsknow.service;

import com.example.tvtcsknow.model.OrderItem;
import com.example.tvtcsknow.repository.OrderItemRepository;
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
public class OrderItemServiceTest {

    @Mock
    private OrderItemRepository orderItemRepository;

    private OrderItemService orderItemService;

    @BeforeEach
    public void setUp() {
        orderItemService = new OrderItemService(orderItemRepository);
    }

    @Test
    public void testGetAllOrderItems() {
        when(orderItemRepository.findAll()).thenReturn(Arrays.asList(new OrderItem(), new OrderItem()));

        assertEquals(2, orderItemService.getAllOrderItems().size());

        verify(orderItemRepository, times(1)).findAll();
    }

    @Test
    public void testGetOrderItemById() {
        OrderItem orderItem = new OrderItem();
        when(orderItemRepository.findById(anyLong())).thenReturn(Optional.of(orderItem));

        assertEquals(orderItem, orderItemService.getOrderItemById(1L));

        verify(orderItemRepository, times(1)).findById(anyLong());
    }

    @Test
    public void testCreateOrderItem() {
        OrderItem orderItem = new OrderItem();
        when(orderItemRepository.save(any(OrderItem.class))).thenReturn(orderItem);

        assertEquals(orderItem, orderItemService.createOrderItem(new OrderItem()));

        verify(orderItemRepository, times(1)).save(any(OrderItem.class));
    }

    @Test
    public void testUpdateOrderItem() {
        OrderItem orderItem = new OrderItem();
        when(orderItemRepository.findById(anyLong())).thenReturn(Optional.of(new OrderItem()));
        when(orderItemRepository.save(any(OrderItem.class))).thenReturn(orderItem);

        OrderItem updatedOrderItem = orderItemService.updateOrderItem(1L, orderItem);

        assertEquals(orderItem, updatedOrderItem);
        verify(orderItemRepository, times(1)).findById(anyLong());
        verify(orderItemRepository, times(1)).save(any(OrderItem.class));
    }

    @Test
    public void testDeleteOrderItem() {
        doNothing().when(orderItemRepository).deleteById(anyLong());

        orderItemService.deleteOrderItem(1L);

        verify(orderItemRepository, times(1)).deleteById(anyLong());
    }
}