package com.river.ms.bbs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ImportResource;

@EnableDiscoveryClient
@EnableAutoConfiguration
@SpringBootApplication
@ImportResource(locations = "classpath*:/applicationContext.xml")
public class BootApp {

	public static void main(String[] args) {
		SpringApplication.run(BootApp.class, args);
	}
}
