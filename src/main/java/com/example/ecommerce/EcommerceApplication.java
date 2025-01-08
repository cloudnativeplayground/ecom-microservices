package com.example.ecommerce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {
        "com.example.ecommerce.controller",
        "com.example.ecommerce.service",
        "com.example.ecommerce.repository",
        "com.example.ecommerce.model",
        "com.example.ecommerce.config"
})
public class EcommerceApplication {

    public static void main(String[] args) {
        SpringApplication.run(EcommerceApplication.class, args);
    }
}
// This code defines a Spring Boot application for an e-commerce platform. It specifies the base packages to scan for components, services, repositories, models, and configurations. The main method runs the application.