package com.example.tvtcsknow.service;

import com.example.tvtcsknow.dto.OrderItemCreateRequest;
import com.example.tvtcsknow.model.Order;
import com.example.tvtcsknow.model.OrderItem;
import com.example.tvtcsknow.model.Product;
import com.example.tvtcsknow.repository.OrderItemRepository;
import com.example.tvtcsknow.repository.OrderRepository;
import com.example.tvtcsknow.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class OrderItemServiceTest {

    @Mock
    private OrderItemRepository orderItemRepository;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private OrderRepository orderRepository;

    private OrderItemService orderItemService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        orderItemService = new OrderItemService(orderItemRepository, productRepository, orderRepository);
    }

    @Test
    public void testGetOrderItemById() {
        OrderItem orderItem = new OrderItem();
        orderItem.setId(1L);
        when(orderItemRepository.findById(1L)).thenReturn(Optional.of(orderItem));

        OrderItem result = orderItemService.getOrderItemById(1L);

        assertEquals(1L, result.getId());
        verify(orderItemRepository, times(1)).findById(1L);
    }

    @Test
    public void testGetOrderItemByIdNotFound() {
        when(orderItemRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> orderItemService.getOrderItemById(1L));
        verify(orderItemRepository, times(1)).findById(1L);
    }

    @Test
    public void testCreateOrderItem() {
        OrderItemCreateRequest request = new OrderItemCreateRequest();
        request.setOrderId(1L);
        request.setProductId(1L);
        request.setQuantity(1);

        Order order = new Order();
        order.setId(1L);
        when(orderRepository.findById(1L)).thenReturn(Optional.of(order));

        Product product = new Product();
        product.setId(1L);
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));

        OrderItem orderItem = new OrderItem();
        orderItem.setOrder(order);
        orderItem.setProduct(product);
        orderItem.setQuantity(1);
        when(orderItemRepository.save(any(OrderItem.class))).thenReturn(orderItem);

        OrderItem result = orderItemService.createOrderItem(request);

        assertEquals(1, result.getQuantity());
        assertEquals(order, result.getOrder());
        assertEquals(product, result.getProduct());
        verify(orderRepository, times(1)).findById(1L);
        verify(productRepository, times(1)).findById(1L);
        verify(orderItemRepository, times(1)).save(any(OrderItem.class));
    }

    @Test
    public void testUpdateOrderItem() {
        OrderItem orderItem = new OrderItem();
        orderItem.setId(1L);
        when(orderItemRepository.findById(1L)).thenReturn(Optional.of(orderItem));

        OrderItem updatedOrderItem = new OrderItem();
        updatedOrderItem.setId(1L);
        when(orderItemRepository.save(any(OrderItem.class))).thenReturn(updatedOrderItem);

        OrderItem result = orderItemService.updateOrderItem(1L, updatedOrderItem);

        assertEquals(1L, result.getId());
        verify(orderItemRepository, times(1)).findById(1L);
        verify(orderItemRepository, times(1)).save(any(OrderItem.class));
    }

    @Test
    public void testUpdateOrderItemNotFound() {
        OrderItem orderItem = new OrderItem();
        orderItem.setId(1L);
        when(orderItemRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> orderItemService.updateOrderItem(1L, orderItem));
        verify(orderItemRepository, times(1)).findById(1L);
    }

    @Test
    public void testDeleteOrderItem() {
        doNothing().when(orderItemRepository).deleteById(1L);

        orderItemService.deleteOrderItem(1L);

        verify(orderItemRepository, times(1)).deleteById(1L);
    }
}