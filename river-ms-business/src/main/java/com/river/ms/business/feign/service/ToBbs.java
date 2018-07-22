package com.river.ms.business.feign.service;

import java.util.Map;

import org.springframework.cloud.netflix.feign.FeignClient;

import com.river.ms.business.feign.FeignConfiguration;

import feign.HeaderMap;
import feign.QueryMap;
import feign.RequestLine;

@FeignClient(name = "${feign.service.bbs}", configuration = FeignConfiguration.class)
public interface ToBbs {

	/**
	 * business模块调用，获取forumID（添加或者查找）
	 * 
	 * @param headerMap
	 * @param queryMap
	 * @return
	 */
	@RequestLine("POST /forum/getForumIdByCode")
	Long getForumIdByCode(@HeaderMap Map<String, Object> headerMap, @QueryMap Map<String, Object> queryMap);
	
	/**
	 * 插入问答
	 * 
	 * @param headerMap
	 * @param queryMap
	 * @return
	 */
	@RequestLine("POST /topic/insertTopicEntity")
	Long insertTopicEntity(@HeaderMap Map<String, Object> headerMap, @QueryMap Map<String, Object> queryMap);
}
