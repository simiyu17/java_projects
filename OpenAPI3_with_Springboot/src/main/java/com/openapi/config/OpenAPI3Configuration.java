package com.openapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class OpenAPI3Configuration {

	@Bean
	  public OpenAPI openAPI3() {
	    return new OpenAPI()
	        .components(new Components())
	        .info(new Info()
	            .title("Book REST API Documentation")
	            .description("This is an example for Spring Boot RESTful service documentation using springdoc-openapi and OpenAPI 3.")
	            .termsOfService("Sample Terms")
	            .contact(new Contact().email("juliandan17@gmail.com"))
	            .license(new License().name("GNU"))
	            .version("1.0.1")
	        );
	  }
}
