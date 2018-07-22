package com.river.ms.res.feign.service;

import java.util.Map;

import org.springframework.cloud.netflix.feign.FeignClient;

import com.river.ms.res.feign.FeignConfiguration;

import feign.HeaderMap;
import feign.QueryMap;
import feign.RequestLine;

@FeignClient(name = "${feign.service.project}", configuration = FeignConfiguration.class)
public interface ToProject {

	/**
	 * 获取项目阶段的大阶段
	 * 
	 * @param headerMap
	 * @param queryMap
	 * @return
	 */
	@RequestLine("POST /projectStage/getParentProjectNodeByFeign")
	Map<Long, Long> getProjectStageNode(@HeaderMap Map<String, Object> headerMap,
			@QueryMap Map<String, Object> queryMap);

	/**
	 * 根据项目ID获取项目阶段id
	 * 
	 * @param request
	 * @param projectId
	 * @return
	 */
	@RequestLine("POST /projectList/getStageIdByProjectId")
	Long getStageIdByProjectId(@HeaderMap Map<String, Object> headerMap, @QueryMap Map<String, Object> queryMap);
}
