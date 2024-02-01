package com.example.tvtcsknow.service;

import com.example.tvtcsknow.model.Product;
import com.example.tvtcsknow.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public List<Product> getAllProducts() {
        log.info("Getting all products");
        return productRepository.findAll();
    }

    public Product getProductById(long id) {
        log.info("Getting product with id: {}", id);
        return productRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Product not found"));

    }

    public Product createProduct(Product product) {
        log.info("Creating product: {}", product);
        return productRepository.save(product);
    }

    public Product updateProduct(long id, Product product) {
        log.info("Updating product with id: {}", id);
        Product productToUpdate = productRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Product not found"));
        productToUpdate.setName(product.getName());
        return productRepository.save(productToUpdate);
    }

    public void deleteProduct(long id) {
        log.info("Deleting product with id: {}", id);
        productRepository.deleteById(id);
    }
}
