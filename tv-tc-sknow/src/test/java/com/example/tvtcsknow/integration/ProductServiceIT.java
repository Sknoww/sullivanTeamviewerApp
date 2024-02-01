package com.example.tvtcsknow.integration;

import com.example.tvtcsknow.model.Product;
import com.example.tvtcsknow.repository.ProductRepository;
import com.example.tvtcsknow.service.ProductService;
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
@ContextConfiguration(initializers = {ProductServiceIT.Initializer.class})
@Testcontainers
public class ProductServiceIT extends BaseServiceTest {

    @Mock
    private ProductRepository productRepository;

    private ProductService productService;

    @BeforeEach
    public void setUp() {
        productService = new ProductService(productRepository);
    }

    @Test
    public void testGetAllProducts() {
        when(productRepository.findAll()).thenReturn(Arrays.asList(new Product(), new Product()));

        assertEquals(2, productService.getAllProducts().size());

        verify(productRepository, times(1)).findAll();
    }

    @Test
    public void testGetProductById() {
        Product product = new Product();
        when(productRepository.findById(anyLong())).thenReturn(Optional.of(product));

        assertEquals(product, productService.getProductById(1L));

        verify(productRepository, times(1)).findById(anyLong());
    }

    @Test
    public void testCreateProduct() {
        Product product = new Product();
        when(productRepository.save(any(Product.class))).thenReturn(product);

        assertEquals(product, productService.createProduct(new Product()));

        verify(productRepository, times(1)).save(any(Product.class));
    }

    @Test
    public void testUpdateProduct() {
        Product product = new Product();
        product.setName("New Product");
        when(productRepository.findById(anyLong())).thenReturn(Optional.of(new Product()));
        when(productRepository.save(any(Product.class))).thenReturn(product);

        Product updatedProduct = productService.updateProduct(1L, product);

        assertEquals("New Product", updatedProduct.getName());
        verify(productRepository, times(1)).findById(anyLong());
        verify(productRepository, times(1)).save(any(Product.class));
    }

    @Test
    public void testDeleteProduct() {
        doNothing().when(productRepository).deleteById(anyLong());

        productService.deleteProduct(1L);

        verify(productRepository, times(1)).deleteById(anyLong());
    }
}
