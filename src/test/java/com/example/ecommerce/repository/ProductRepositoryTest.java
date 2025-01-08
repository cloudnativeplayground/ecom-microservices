package com.example.ecommerce.repository;

import com.example.ecommerce.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;
import com.example.ecommerce.model.Category;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    private Product testProduct;

    @BeforeEach
    void setUp() {
        testProduct = new Product(null, "Product1", "Description", 100.0, "ImageUrl1", new Category());
        productRepository.save(testProduct);
    }

    @Test
    void testFindById() {
        Optional<Product> product = productRepository.findById(testProduct.getId());

        assertTrue(product.isPresent());
        assertEquals("Test Product", product.get().getName());
    }

    @Test
    void testFindAll() {
        Iterable<Product> products = productRepository.findAll();

        assertNotNull(products);
        assertTrue(products.iterator().hasNext());
    }

    @Test
    void testDeleteProduct() {
        Long id = testProduct.getId();
        productRepository.delete(testProduct);

        Optional<Product> deletedProduct = productRepository.findById(id);
        assertFalse(deletedProduct.isPresent());
    }
}
