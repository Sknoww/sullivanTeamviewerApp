package com.example.tvtcsknow.controller;

import com.example.tvtcsknow.model.Product;
import com.example.tvtcsknow.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@Tag(name = "Product Controller", description = "Product API")
@AllArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("")
    @Operation(summary = "Get all products")
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get product by id")
    public Product getProductById(@PathVariable Long id) {
        return productService.getProductById(id);
    }

    @PostMapping("")
    @Operation(summary = "Create new product")
    public Product createProduct(@RequestBody Product product) {
        return productService.createProduct(product);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update product by id")
    public Product updateProduct(@PathVariable Long id, @RequestBody Product product) {
        return productService.updateProduct(id, product);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete product by id")
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
    }
}
