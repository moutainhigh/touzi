package com.river.ms.system.service.feign;

import java.util.Map;

import org.springframework.cloud.netflix.feign.FeignClient;

import com.river.ms.system.feign.FeignConfiguration;

import feign.HeaderMap;
import feign.QueryMap;
import feign.RequestLine;

@FeignClient(name = "${feign.service.res}", configuration = FeignConfiguration.class)
public interface ToRes {

	/**
	 * 获取用户的认证信息，以及resId
	 * 
	 * @param userId
	 * @param itcode
	 * @return
	 */
	@RequestLine("POST /resList/getResAuthentication")
	public Map<String, String> getResAuthentication(@HeaderMap Map<String, Object> headerMap,
			@QueryMap Map<String, Object> queryMap);

}
