package com.river.ms.business.feign.service;

import java.util.Map;

import org.springframework.cloud.netflix.feign.FeignClient;

import com.river.ms.business.feign.FeignConfiguration;

import feign.HeaderMap;
import feign.QueryMap;
import feign.RequestLine;

@FeignClient(url="${feign.activiti.SSO}", configuration = FeignConfiguration.class , name="ToSSO")
public interface ToSSO {
	@RequestLine("GET /MailServlet")
	String mail(@HeaderMap Map<String, Object> headerMap,
			@QueryMap Map<String, Object> queryMap);
}
