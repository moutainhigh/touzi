package com.river.ms.ribbon;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import com.river.ms.ribbon.filter.LoginFilter;

@EnableZuulProxy
@EnableDiscoveryClient
@SpringBootApplication
@EnableCircuitBreaker
@Configuration
public class BootApp {

	@Bean
	@LoadBalanced
	RestTemplate restTemplate() {
		return new RestTemplate();
	}

	public static void main(String[] args) {
		SpringApplication.run(BootApp.class, args);
	}

	@Bean
	public AccessFilter accessFilter() {
		return new AccessFilter();
	}

	@Bean
	public FilterRegistrationBean crossServlet() {
		FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
		filterRegistrationBean.setFilter(new CrossFilter());
		filterRegistrationBean.addUrlPatterns("/*");
		return filterRegistrationBean;
	}

	@Bean
	public FilterRegistrationBean longinFilter() {
		Map<String, String> initParameters = new HashMap<String, String>();
		initParameters.put("loginURL", "/system/user/login");
		initParameters.put("backstageLoginURL", "/system/user/backstageLogin");
		initParameters.put("isBackstageLoginURL", "/system/user/isBackstageLogin");
		initParameters.put("loginOut", "/system/user/userLogout");
		initParameters.put("accessToken", "/accessToken");
		initParameters.put("pleaseLogin", "/system/user/pleaseLogin");
		initParameters.put("documentUpload", "/dfs/document/getDocumentById");
		FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
		filterRegistrationBean.setInitParameters(initParameters);
		filterRegistrationBean.setFilter(new LoginFilter());
		filterRegistrationBean.addUrlPatterns("/*");
		return filterRegistrationBean;
	}

}
