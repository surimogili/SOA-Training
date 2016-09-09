package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
/*
 * @EnableDiscoveryClient is the main annotation to discover(register) this service in consul
 * 
 */
@SpringBootApplication
@EnableMongoRepositories(basePackages="com.example.repository")
@EnableDiscoveryClient
public class OrderMsApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrderMsApplication.class, args);
	}
}
