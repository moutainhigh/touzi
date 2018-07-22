package com.river.ms.business.feign.service;

import java.util.Map;

import org.springframework.cloud.netflix.feign.FeignClient;

import com.river.core.entity.UserEntity;
import com.river.ms.business.feign.FeignConfiguration;

import feign.HeaderMap;
import feign.QueryMap;
import feign.RequestLine;

@FeignClient(name = "${feign.service.system}", configuration = FeignConfiguration.class)
public interface ToSystem {

	/**
	 * 根据itCode获取用户信息
	 * @param headerMap
	 * @param queryMap
	 * @return
	 */
	@RequestLine("POST /user/getUserMessageByResId")
	UserEntity getUserMessageByResId(@HeaderMap Map<String, Object> headerMap,
			@QueryMap Map<String, Object> queryMap);
	
	@RequestLine("POST /user/getGongSi")
	Map<Long,String> getGongSi(@HeaderMap Map<String, Object> headerMap,
			@QueryMap Map<String, Object> queryMap);
}
