package com.example.ecommerce.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@Configuration
@EnableEurekaServer // This starts the Eureka server if this service is used for service discovery
@PropertySource("classpath:application.properties")
public class EurekaConfig {

    // You can add more beans or methods for service registration and discovery
}
// This configuration class sets up a basic Eureka server for service discovery in a microservices architecture.