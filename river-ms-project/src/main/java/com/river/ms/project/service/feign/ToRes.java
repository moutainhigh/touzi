package com.river.ms.project.service.feign;

import java.util.List;
import java.util.Map;

import org.springframework.cloud.netflix.feign.FeignClient;

import com.river.core.result.JsonResult;
import com.river.ms.project.feign.FeignConfiguration;

import feign.HeaderMap;
import feign.QueryMap;
import feign.RequestLine;

@FeignClient(name = "${feign.service.res}", configuration = FeignConfiguration.class)
public interface ToRes {


	/**
	 * 获取重点关注的项目id
	 * 
	 * @param userId
	 * @param itcode
	 * @return
	 */
	@RequestLine("POST /projectFocuson/selectProjectIdsFocusonList")
	public Map<Long, Long> selectProjectIdsFocusonList(@HeaderMap Map<String, Object> headerMap);
	
	/**
	 * 根据用户的itcode获取用户ID
	 * @param headerMap
	 * @param queryMap
	 * @return
	 */
	@RequestLine("POST /resList/getResId")
	public Long getResId(@HeaderMap Map<String, Object> headerMap, @QueryMap Map<String, Object> queryMap);
	
	@RequestLine("POST /resList/getComLeaderIds")
	public Map<String,List<Long>> getComLeaderIds(@HeaderMap Map<String, Object> headerMap, @QueryMap Map<String, Object> queryMap);
	
	@RequestLine("POST /resList/getFuNengQunIds")
	public Map<String,List<Long>> getFuNengQunIds(@HeaderMap Map<String, Object> headerMap, @QueryMap Map<String, Object> queryMap);
}
