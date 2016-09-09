package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;

@SpringBootApplication
@EnableDiscoveryClient
@RibbonClient(configuration = CustomLoadBalancerConfiguration.class, name="lb1")
public class OrderLBApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrderLBApplication.class, args);
	}
}
