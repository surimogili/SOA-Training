package com.example;

import javax.servlet.Filter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.filter.ShallowEtagHeaderFilter;

@SpringBootApplication
@EnableDiscoveryClient
@EnableWebSecurity
public class MSOauthServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(MSOauthServerApplication.class, args);
	}
	
	@Bean
	public Filter shallowEtagHeaderFilter()
	{
		return new ShallowEtagHeaderFilter();
	}
}
