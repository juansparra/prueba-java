package com.example.pruebajava;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


@EnableDiscoveryClient
@SpringBootApplication
public class PruebaJavaApplication {

	public static void main(String[] args) {
		SpringApplication.run(PruebaJavaApplication.class, args);
	}

}
