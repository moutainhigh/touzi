package com.river.ms.cms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ImportResource;

@EnableDiscoveryClient
@EnableEurekaClient
@EnableAutoConfiguration
@SpringBootApplication
@ImportResource(locations = "classpath*:/applicationContext.xml")
public class BootApp {

	public static void main(String[] args) {
		SpringApplication.run(BootApp.class, args);
	}
}
