package com.river.ms.res.feign.service;

import java.util.Map;

import org.springframework.cloud.netflix.feign.FeignClient;

import com.river.ms.res.feign.FeignConfiguration;

import feign.HeaderMap;
import feign.QueryMap;
import feign.RequestLine;

@FeignClient(name = "${feign.service.system}", configuration = FeignConfiguration.class)
public interface ToSystem {

	@RequestLine("POST /employee/codeIsExit")
	Boolean codeIsExit(@HeaderMap Map<String, Object> headerMap,
			@QueryMap Map<String, Object> queryMap);
	
	@RequestLine("POST /user/insertUser")
	Long insertUser(@HeaderMap Map<String, Object> headerMap,
			@QueryMap Map<String, Object> queryMap);
	
	@RequestLine("POST /user/getGongSi")
	Map<Long,String> getGongSi(@HeaderMap Map<String, Object> headerMap,
			@QueryMap Map<String, Object> queryMap);
}
