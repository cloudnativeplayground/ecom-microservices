package com.example.ecommerce.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2 // Enable Swagger support
public class SwaggerConfig {

    /**
     * Configures Swagger to generate documentation for REST APIs.
     */
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.example.ecommerce"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(new ApiInfoBuilder().title("E-Commerce Microservices API")
                        .description("API Documentation for the E-Commerce Microservices Platform")
                        .version("1.0")
                        .build());
    }
}
// This configuration class sets up Swagger to document the REST APIs of the E-Commerce microservices platform.