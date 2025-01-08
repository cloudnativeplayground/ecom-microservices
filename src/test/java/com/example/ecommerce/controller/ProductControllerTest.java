package com.example.ecommerce.controller;

import com.example.ecommerce.service.ProductService;
import com.example.ecommerce.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import com.example.ecommerce.model.Category;
import static org.mockito.ArgumentMatchers.anyLong;
import java.util.Optional;

public class ProductControllerTest {

    @Mock
    private ProductService productService;

    @InjectMocks
    private ProductController productController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllProducts() {
        List<Product> mockProducts = Arrays.asList(
                new Product(1L, "Product1", "Description1", 100.0, "ImageUrl1", new Category()),
                new Product(2L, "Product2", "Description2", 200.0, "ImageUrl2", new Category())
        );

        when(productService.getAllProducts()).thenReturn(mockProducts);

        ResponseEntity<List<Product>> response = new ResponseEntity<>(productController.getAllProducts(), HttpStatus.OK);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockProducts.size(), response.getBody().size());
        verify(productService, times(1)).getAllProducts();
    }

    @Test
    void testGetProductById() {
        Product mockProduct = new Product(1L, "Product1", "Description", 100.0, "ImageUrl", new Category());

        when(productService.getProductById(1L)).thenReturn(Optional.of(mockProduct));

        ResponseEntity<Product> response = productController.getProductById(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockProduct.getName(), response.getBody().getName());
    }
}
// ProductController.java