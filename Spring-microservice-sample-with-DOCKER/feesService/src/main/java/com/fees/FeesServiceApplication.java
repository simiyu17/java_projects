package com.fees;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class FeesServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(FeesServiceApplication.class, args);
	}

}
