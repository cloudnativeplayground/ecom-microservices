package com.example.ecommerce.config;

import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.web.client.RestTemplate;

@Configuration
@EnableEurekaClient  // Enable Eureka Client for service discovery
@EnableDiscoveryClient // To register with Eureka as a discovery client
public class ServiceConfig {

    /**
     * Load-balanced RestTemplate for making service-to-service communication.
     */
    @Bean
    @LoadBalanced  // To enable load balancing using Ribbon (if using Eureka)
    public RestTemplate loadBalancedRestTemplate() {
        return new RestTemplate();
    }
}
// This configuration class sets up a load-balanced RestTemplate for making service-to-service calls in a microservices architecture.   It uses Spring Cloud's Eureka for service discovery and Ribbon for client-side load balancing.
