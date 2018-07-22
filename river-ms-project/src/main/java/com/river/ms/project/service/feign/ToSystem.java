package com.river.ms.project.service.feign;

import java.util.List;
import java.util.Map;

import org.springframework.cloud.netflix.feign.FeignClient;

import com.river.ms.project.feign.FeignConfiguration;

import feign.HeaderMap;
import feign.QueryMap;
import feign.RequestLine;

@FeignClient(name = "${feign.service.system}", configuration = FeignConfiguration.class)
public interface ToSystem {

	/**
	 * 获取角色名称
	 * 
	 * @param userId
	 * @param itcode
	 * @return
	 */
	@RequestLine("POST /role/getRoleName")
	String getRoleName(@HeaderMap Map<String, Object> headerMap, @QueryMap Map<String, Object> queryMap);
	
	@RequestLine("POST /organization/getOrgCodes")
	List<String> getOrgCodes(@HeaderMap Map<String, Object> headerMap, @QueryMap Map<String, Object> queryMap);
	
}
