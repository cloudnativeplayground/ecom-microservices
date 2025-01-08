package com.example.ecommerce.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.client.RestTemplate;

@Configuration
public class Config {

    /**
     * RestTemplate bean for making REST API calls between services.
     */
    @Bean
    @Profile("!test")  // Don't create RestTemplate for 'test' profile
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
//></>This configuration class defines a RestTemplate bean that can be used for making REST API calls between different services in the application. The RestTemplate bean is only created if the active profile is not 'test', ensuring that it is not instantiated during unit tests.