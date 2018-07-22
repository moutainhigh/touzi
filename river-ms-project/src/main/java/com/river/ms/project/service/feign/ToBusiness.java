package com.river.ms.project.service.feign;

import java.util.Map;

import org.springframework.cloud.netflix.feign.FeignClient;

import com.river.core.result.JsonResult;
import com.river.ms.project.feign.FeignConfiguration;

import feign.HeaderMap;
import feign.QueryMap;
import feign.RequestLine;

@FeignClient(name = "${feign.service.business}", configuration = FeignConfiguration.class)
public interface ToBusiness {

	/**
	 * 获取用户的认证信息，以及resId
	 * 
	 * @param userId
	 * @param itcode
	 * @return
	 */
	@RequestLine("POST /ProjectListAction/getProjectList")
	Map<Long,Long> getProjectList(@HeaderMap Map<String, Object> headerMap, @QueryMap Map<String, Object> queryMap);
	
	/**
	 * 获取项目关键指标统计
	 * @param headerMap
	 * @param queryMap List<Long>projectIds
	 * @return
	 */
	@RequestLine("POST /projectKeypoint/projectKeypointStatistics")
	Map<String, Object> projectKeypointStatistics(@HeaderMap Map<String, Object> headerMap, @QueryMap Map<String, Object> queryMap);
	
	/**
	 * 获取用户要操作的项目ID
	 * @param headerMap
	 * @param queryMap
	 * @return
	 */
	@RequestLine("POST /projectTodo/getProjectToDo")
	JsonResult getProjectToDo(@HeaderMap Map<String, Object> headerMap, @QueryMap Map<String, Object> queryMap);
	
	/**
	 * 添加项目用户操作信息
	 * @param headerMap
	 * @param queryMap
	 * @return
	 */
	@RequestLine("POST /projectTodo/insertProjectToDo")
	JsonResult insertProjectToDo(@HeaderMap Map<String, Object> headerMap, @QueryMap Map<String, Object> queryMap);
	
	/**
	 * 获取专家评议过的项目ID
	 * @param headerMap
	 * @param queryMap
	 * @return
	 */
	@RequestLine("POST /projectExpert/getProjectsCountByResId")
	JsonResult getProjectsCountByResId(@HeaderMap Map<String, Object> headerMap, @QueryMap Map<String, Object> queryMap);
	
	@RequestLine("POST /projectFlow/getFlowId")
	Long getFlowId(@HeaderMap Map<String, Object> headerMap, @QueryMap Map<String, Object> queryMap);
}
