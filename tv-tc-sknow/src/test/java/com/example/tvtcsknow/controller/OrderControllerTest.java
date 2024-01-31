package com.example.tvtcsknow.controller;

import com.example.tvtcsknow.model.Order;
import com.example.tvtcsknow.service.OrderService;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class OrderControllerTest {

    @Mock
    private OrderService mockOrderService;

    @InjectMocks
    private OrderController orderController;

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(orderController).build();
    }

    @AfterAll
    public static void tearDown() throws Exception {
        MockitoAnnotations.openMocks(OrderControllerTest.class).close();
    }

    @Test
    public void testGetAllOrders() throws Exception {
        when(mockOrderService.getAllOrders()).thenReturn(Arrays.asList(new Order(), new Order()));

        mockMvc.perform(get("/api/orders"))
                .andExpect(status().isOk());

        verify(mockOrderService, times(1)).getAllOrders();
    }

    @Test
    public void testGetOrderById() throws Exception {
        when(mockOrderService.getOrderById(anyLong())).thenReturn(new Order());

        mockMvc.perform(get("/api/orders/1"))
                .andExpect(status().isOk());

        verify(mockOrderService, times(1)).getOrderById(anyLong());
    }

    @Test
    public void testCreateOrder() throws Exception {
        when(mockOrderService.createOrder(any(Order.class))).thenReturn(new Order());

        mockMvc.perform(post("/api/orders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isOk());

        verify(mockOrderService, times(1)).createOrder(any(Order.class));
    }

    @Test
    public void testUpdateOrder() throws Exception {
        when(mockOrderService.updateOrder(anyLong(), any(Order.class))).thenReturn(new Order());

        mockMvc.perform(put("/api/orders/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isOk());

        verify(mockOrderService, times(1)).updateOrder(anyLong(), any(Order.class));
    }

    @Test
    public void testDeleteOrder() throws Exception {
        doNothing().when(mockOrderService).deleteOrder(anyLong());

        mockMvc.perform(delete("/api/orders/1"))
                .andExpect(status().isOk());

        verify(mockOrderService, times(1)).deleteOrder(anyLong());
    }
}
