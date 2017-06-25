package com.csg.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class SpringCloudServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringCloudServerApplication.class, args);
	}
}
