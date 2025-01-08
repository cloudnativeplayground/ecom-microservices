package com.example.ecommerce;

import com.example.ecommerce.controller.ProductController;
import com.example.ecommerce.service.ProductService;
import com.example.ecommerce.model.Product;
import com.example.ecommerce.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpMethod;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import java.util.List;
import com.example.ecommerce.model.Category;
import static org.mockito.ArgumentMatchers.anyLong;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)  // Enable Mockito for the test class
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)  // This annotation tells Spring Boot to start an embedded web server
public class EcommerceApplicationTests {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Mock
    private ProductService productService;

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductController productController;

    private Product testProduct;

    @BeforeEach
    public void setUp() {
        // This method will be executed before each test
        testProduct = new Product(1L, "Test Product", "Test Description", 100.0, "ImageUrl", new Category());
        // Mock the service layer
        when(productService.getProductById(anyLong())).thenReturn(Optional.of(testProduct));
    }

    @Test
    void contextLoads() {
        // This is a basic test to check if the Spring application context is loaded correctly.
        assertDoesNotThrow(() -> {
            // No exception should be thrown
        });
    }

    @Test
    void testGetProductById() {
        // Test if the /product/{id} endpoint returns the expected product
        ResponseEntity<Product> response = restTemplate.getForEntity("http://localhost:" + port + "/ecommerce/products/1", Product.class);

        assertEquals(200, response.getStatusCodeValue());  // Assert that the status code is 200 OK
        assertEquals(testProduct.getName(), response.getBody().getName());  // Assert that the name matches
    }

    @Test
    void testGetAllProducts() {
        // Test if the /products endpoint returns a list of products
        List<Product> mockProducts = List.of(
                new Product(1L, "Product1", "Description", 100.0, "ImageUrl1", new Category()),
new Product(2L, "Test Product 2", "Description", 150.0, "ImageUrl2", new Category())
        );

        when(productService.getAllProducts()).thenReturn(mockProducts);

        ResponseEntity<List> response = restTemplate.exchange("http://localhost:" + port + "/ecommerce/products", HttpMethod.GET, null, List.class);

        assertEquals(200, response.getStatusCodeValue());  // Assert that the status code is 200 OK
        assertEquals(mockProducts.size(), response.getBody().size());  // Assert that the number of products matches
    }

    @Test
    void testSaveProduct() {
        // Test if we can save a product using the POST API
       Product newProduct = new Product(null, "Product1", "Description", 100.0, "ImageUrl", new Category());

        when(productRepository.save(newProduct)).thenReturn(new Product(3L, "Product1", "Description", 100.0, "ImageUrl", new Category())); // Mock the repository's save method

        // Simulate a POST request to save the new product
        ResponseEntity<Product> response = restTemplate.postForEntity("http://localhost:" + port + "/ecommerce/products", newProduct, Product.class);

        assertEquals(201, response.getStatusCodeValue());  // Assert that the status code is 201 Created
        assertEquals("New Product", response.getBody().getName());  // Assert that the name matches
    }

    @Test
    void testDeleteProduct() {
        // Test if we can delete a product
        doNothing().when(productRepository).deleteById(1L);

        restTemplate.delete("http://localhost:" + port + "/ecommerce/products/1");

        // Verify that the product repository's deleteById method was called
        verify(productRepository, times(1)).deleteById(1L);
    }

    @Test
    void testProductServiceMethod() {
        // Test a simple service layer method directly (e.g., getProductById)
        Product product = productService.getProductById(1L).orElse(null);

        assertNotNull(product);  // Assert that the product is not null
        assertEquals("Test Product", product.getName());  // Assert that the name matches the mock data
    }
}
// Note: This code assumes that you have a ProductController, ProductService, and ProductRepository set up in your Spring Boot application.