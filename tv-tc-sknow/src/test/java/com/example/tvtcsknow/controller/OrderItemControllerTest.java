package com.example.tvtcsknow.controller;

import com.example.tvtcsknow.dto.OrderItemCreateRequest;
import com.example.tvtcsknow.model.OrderItem;
import com.example.tvtcsknow.service.OrderItemService;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class OrderItemControllerTest {

    @Mock
    private OrderItemService mockOrderItemService;

    private OrderItemController orderItemController;

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        orderItemController = new OrderItemController(mockOrderItemService);
        mockMvc = MockMvcBuilders.standaloneSetup(orderItemController).build();
    }

    @AfterAll
    public static void tearDown() throws Exception {
        MockitoAnnotations.openMocks(OrderItemControllerTest.class).close();
    }

    @Test
    public void testGetAllOrderItems() throws Exception {
        when(mockOrderItemService.getAllOrderItems()).thenReturn(Arrays.asList(new OrderItem(), new OrderItem()));

        mockMvc.perform(get("/api/order-items"))
                .andExpect(status().isOk());

        verify(mockOrderItemService, times(1)).getAllOrderItems();
    }

    @Test
    public void testGetOrderItemById() throws Exception {
        when(mockOrderItemService.getOrderItemById(anyLong())).thenReturn(new OrderItem());

        mockMvc.perform(get("/api/order-items/1"))
                .andExpect(status().isOk());

        verify(mockOrderItemService, times(1)).getOrderItemById(anyLong());
    }

    @Test
    public void testCreateOrderItem() throws Exception {
        when(mockOrderItemService.createOrderItem(any(OrderItemCreateRequest.class))).thenReturn(new OrderItem());

        mockMvc.perform(post("/api/order-items")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isOk());

        verify(mockOrderItemService, times(1)).createOrderItem(any(OrderItemCreateRequest.class));
    }

    @Test
    public void testUpdateOrderItem() throws Exception {
        when(mockOrderItemService.updateOrderItem(anyLong(), any(OrderItem.class))).thenReturn(new OrderItem());

        mockMvc.perform(put("/api/order-items/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isOk());

        verify(mockOrderItemService, times(1)).updateOrderItem(anyLong(), any(OrderItem.class));
    }

    @Test
    public void testDeleteOrderItem() throws Exception {
        doNothing().when(mockOrderItemService).deleteOrderItem(anyLong());

        mockMvc.perform(delete("/api/order-items/1"))
                .andExpect(status().isOk());

        verify(mockOrderItemService, times(1)).deleteOrderItem(anyLong());
    }
}