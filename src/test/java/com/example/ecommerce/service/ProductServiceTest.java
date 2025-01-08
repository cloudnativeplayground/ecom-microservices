package com.example.ecommerce.service;

import com.example.ecommerce.model.Product;
import com.example.ecommerce.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import com.example.ecommerce.model.Category;

public class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllProducts() {
        List<Product> mockProducts = Arrays.asList(
               new Product(1L, "Test Product", "Test Description", 100.0, "ImageUrl", new Category()),
new Product(2L, "Testing Product", "Testing Description", 200.0, "ImageUrl", new Category())
        );

        when(productRepository.findAll()).thenReturn(mockProducts);

        List<Product> products = productService.getAllProducts();

        assertNotNull(products);
        assertEquals(2, products.size());
        verify(productRepository, times(1)).findAll();
    }

    @Test
    void testGetProductById() {
        Product mockProduct = new Product(1L, "Test Product", "Test Description", 100.0, "ImageUrl", new Category());

        when(productRepository.findById(1L)).thenReturn(Optional.of(mockProduct));

        Product product = productService.getProductById(1L).orElse(null);;

        assertNotNull(product);
        assertEquals("Product1", product.getName());
        verify(productRepository, times(1)).findById(1L);
    }
}
