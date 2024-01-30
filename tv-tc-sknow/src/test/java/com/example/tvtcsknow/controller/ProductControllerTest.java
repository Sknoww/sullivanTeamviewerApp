package com.example.tvtcsknow.controller;

import com.example.tvtcsknow.model.Product;
import com.example.tvtcsknow.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;

import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ProductControllerTest {

    @Mock
    private ProductService mockProductService;

    private ProductController productController;

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        initMocks(this);
        productController = new ProductController(mockProductService);
        mockMvc = MockMvcBuilders.standaloneSetup(productController).build();
    }

    @Test
    public void testGetAllProducts() throws Exception {
        when(mockProductService.getAllProducts()).thenReturn(Arrays.asList(new Product(), new Product()));

        mockMvc.perform(get("/api/products"))
                .andExpect(status().isOk());

        verify(mockProductService, times(1)).getAllProducts();
    }

    @Test
    public void testGetProductById() throws Exception {
        when(mockProductService.getProductById(anyLong())).thenReturn(new Product());

        mockMvc.perform(get("/api/products/1"))
                .andExpect(status().isOk());

        verify(mockProductService, times(1)).getProductById(anyLong());
    }

    @Test
    public void testCreateProduct() throws Exception {
        when(mockProductService.createProduct(any(Product.class))).thenReturn(new Product());

        mockMvc.perform(post("/api/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isOk());

        verify(mockProductService, times(1)).createProduct(any(Product.class));
    }

    @Test
    public void testUpdateProduct() throws Exception {
        when(mockProductService.updateProduct(anyLong(), any(Product.class))).thenReturn(new Product());

        mockMvc.perform(put("/api/products/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isOk());

        verify(mockProductService, times(1)).updateProduct(anyLong(), any(Product.class));
    }

    @Test
    public void testDeleteProduct() throws Exception {
        doNothing().when(mockProductService).deleteProduct(anyLong());

        mockMvc.perform(delete("/api/products/1"))
                .andExpect(status().isOk());

        verify(mockProductService, times(1)).deleteProduct(anyLong());
    }
}