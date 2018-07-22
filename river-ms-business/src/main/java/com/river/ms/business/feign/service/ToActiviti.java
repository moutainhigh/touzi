package com.river.ms.business.feign.service;

import java.util.Map;

import org.springframework.cloud.netflix.feign.FeignClient;

import com.river.core.result.JsonResult;
import com.river.ms.business.entity.ProcessResult;
import com.river.ms.business.feign.FeignConfiguration;

import feign.HeaderMap;
import feign.QueryMap;
import feign.RequestLine;

@FeignClient(url="${feign.activiti.business}", configuration = FeignConfiguration.class , name="ToActiviti")
public interface ToActiviti {

	/**
	 * 启动项目流程
	 * @param headerMap
	 * @param queryMap
	 * @return
	 */
	@RequestLine("POST /start")
	JsonResult start(@HeaderMap Map<String, Object> headerMap,
			@QueryMap Map<String, Object> queryMap);
	
	/**
	 * 传输项目节点，参数，获取下一阶段。
	 * @param headerMap
	 * @param queryMap
	 * @return
	 */
	@RequestLine("POST /complete/brainAudit")
	JsonResult nodeEnd(@HeaderMap Map<String, Object> headerMap,
			@QueryMap Map<String, Object> queryMap);
	/**
	 * 根据流程实例ID获取流程相关信息 processInstanceId
	 * @param headerMap
	 * @param queryMap
	 * @return
	 */
	@RequestLine("POST /getProcess")
	JsonResult getProcess(@HeaderMap Map<String, Object> headerMap,
			@QueryMap Map<String, Object> queryMap);
	/**
	 * 获取历史流程，参数processInstanceId
	 * @param headerMap
	 * @param queryMap
	 * @return
	 */
	@RequestLine("POST /history")
	JsonResult getHistory(@HeaderMap Map<String, Object> headerMap,
			@QueryMap Map<String, Object> queryMap);
}
