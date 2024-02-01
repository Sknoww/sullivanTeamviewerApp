package com.example.tvtcsknow.integration;

import com.example.tvtcsknow.dto.OrderItemCreateRequest;
import com.example.tvtcsknow.model.Order;
import com.example.tvtcsknow.model.OrderItem;
import com.example.tvtcsknow.model.Product;
import com.example.tvtcsknow.repository.OrderItemRepository;
import com.example.tvtcsknow.repository.OrderRepository;
import com.example.tvtcsknow.repository.ProductRepository;
import com.example.tvtcsknow.service.OrderItemService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
@ContextConfiguration(initializers = {OrderItemServiceIT.Initializer.class})
@Testcontainers
public class OrderItemServiceIT extends BaseServiceTest {

    @Mock
    private OrderItemRepository orderItemRepository;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private OrderRepository orderRepository;

    private OrderItemService orderItemService;

    @BeforeEach
    public void setUp() {
        orderItemService = new OrderItemService(orderItemRepository, productRepository, orderRepository);
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
        OrderItemCreateRequest request = new OrderItemCreateRequest(1L, 1L, 1);
        when(orderItemRepository.save(any(OrderItem.class))).thenReturn(orderItem);
        when(orderRepository.findById(1L)).thenReturn(Optional.of(new Order()));
        when(productRepository.findById(1L)).thenReturn(Optional.of(new Product()));

        assertEquals(orderItem, orderItemService.createOrderItem(request));

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