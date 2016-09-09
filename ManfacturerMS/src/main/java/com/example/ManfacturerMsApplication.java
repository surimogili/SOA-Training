package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories(basePackages="com.example.repository")
@EnableDiscoveryClient
@EnableFeignClients
public class ManfacturerMsApplication {

	public static void main(String[] args) {
		SpringApplication.run(ManfacturerMsApplication.class, args);
	}
}
