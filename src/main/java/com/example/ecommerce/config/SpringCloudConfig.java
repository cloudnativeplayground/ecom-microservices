package com.example.ecommerce.config;

import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class SpringCloudConfig {

    /**
     * RestTemplate used for communication with a Spring Cloud Config Server.
     */
    @Bean
    @RefreshScope  // Enabling refresh for the bean to pull new configurations from the config server
    public RestTemplate configServerRestTemplate() {
        return new RestTemplate();
    }
}
// This configuration class defines a RestTemplate bean that can be used to communicate with a Spring Cloud Config Server.
// The @RefreshScope annotation allows the bean to be refreshed with new configurations without restarting the application.