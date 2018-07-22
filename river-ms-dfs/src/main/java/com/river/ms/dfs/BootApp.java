package com.river.ms.dfs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;

import com.github.tobato.fastdfs.FdfsClientConfig;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Import(FdfsClientConfig.class)
@EnableDiscoveryClient
@EnableAutoConfiguration
@SpringBootApplication
@EnableEurekaClient
@EnableSwagger2
@ImportResource(locations = "classpath*:/applicationContext.xml")
public class BootApp {

	public static void main(String[] args) {
		SpringApplication.run(BootApp.class, args);
	}
}
