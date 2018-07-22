package com.river.ms.business.feign.service;

import java.util.List;
import java.util.Map;

import org.springframework.cloud.netflix.feign.FeignClient;

import com.river.core.result.JsonResult;
import com.river.ms.business.feign.FeignConfiguration;

import feign.HeaderMap;
import feign.QueryMap;
import feign.RequestLine;

@FeignClient(name = "${feign.service.project}", configuration = FeignConfiguration.class)
public interface ToProject {

	/**
	 * 设置项目状态
	 * 
	 * @param headerMap
	 * @param queryMap
	 * @RequestParam(value = "entityId") Long entityId,
	 * @RequestParam(value = "state") Integer state
	 * @return
	 */
	@RequestLine("POST /projectList/setProjectState")
	Boolean setProjectState(@HeaderMap Map<String, Object> headerMap, @QueryMap Map<String, Object> queryMap);

	/**
	 * 设置项目阶段
	 * 
	 * @param headerMap
	 * @param queryMap
	 * @RequestParam(value = "entityId") Long entityId,
	 * @RequestParam(value = "stageCode") String stageCode
	 * @return
	 */
	@RequestLine("POST /projectList/setProjectStage")
	Boolean setProjectStage(@HeaderMap Map<String, Object> headerMap, @QueryMap Map<String, Object> queryMap);

	/**
	 * 根据项目阶段ID查找项目ID
	 * 
	 * @param headerMap
	 * @param queryMap
	 * @return
	 */
	@RequestLine("POST /projectList/getProjectIds")
	Map<Long, Long> getProjectIds(@HeaderMap Map<String, Object> headerMap, @QueryMap Map<String, Object> queryMap);

	/**
	 * 根据用户ID和用户角色获取项目ID
	 * 
	 * @param headerMap
	 * @param queryMap
	 * @return
	 */
	@RequestLine("POST /projectList/getProjectIdsByUserIdAndUserRoles")
	Map<Long, Long> getProjectIdsByUserIdAndUserRoles(@HeaderMap Map<String, Object> headerMap,
			@QueryMap Map<String, Object> queryMap);

	/**
	 * 获取项目阶段的大阶段（根据项目阶段ID）
	 * 
	 * @param headerMap
	 * @param queryMap
	 *            stageCode(项目阶段ID)
	 * @return
	 */
	@RequestLine("POST /projectStage/getParentProjectNodeByFeign")
	Map<Long, Long> getProjectStageNode(@HeaderMap Map<String, Object> headerMap,
			@QueryMap Map<String, Object> queryMap);

	/**
	 * 根据项目阶段code获取项目阶段ID
	 * 
	 * @param headerMap
	 * @param queryMap
	 *            stageCode
	 * @return
	 */
	@RequestLine("POST /projectStage/getProjectStageIdByCode")
	Map<Long, Long> getProjectStageIdByCode(@HeaderMap Map<String, Object> headerMap,
			@QueryMap Map<String, Object> queryMap);

	/**
	 * 根据项目ID和项目阶段获取可操作角色和用户
	 * 
	 * @return projectId
	 */
	@RequestLine("POST /projectList/getRoleAndUserByProjectIdAndStage")
	Map<Long, List<Long>> getRoleAndUserByProjectIdAndStage(@HeaderMap Map<String, Object> headerMap,
			@QueryMap Map<String, Object> queryMap);

	/**
	 * 根据项目ID获取项目阶段code
	 * 
	 * @return projectId
	 */
	@RequestLine("POST /projectList/getStageByProjectId")
	String getStageByProjectId(@HeaderMap Map<String, Object> headerMap, @QueryMap Map<String, Object> queryMap);

	/**
	 * 设置流程实例ID
	 * 
	 * @param headerMap
	 * @param queryMap
	 * @return
	 */
	@RequestLine("POST /projectList/setLiuchengshiliIdByProjectId")
	Boolean setLiuchengshiliIdByProjectId(@HeaderMap Map<String, Object> headerMap,
			@QueryMap Map<String, Object> queryMap);

	/**
	 * 获取流程实例ID
	 * 
	 * @param headerMap
	 * @param queryMap
	 * @return
	 */
	@RequestLine("POST /projectList/getLiuchengshiliIdByProjectId")
	String getLiuchengshiliIdByProjectId(@HeaderMap Map<String, Object> headerMap,
			@QueryMap Map<String, Object> queryMap);

	/**
	 * 根据项目阶段code获取下一个阶段的code
	 * 
	 * @param headerMap
	 * @param queryMap
	 * @return
	 */
	@RequestLine("POST /projectStage/getProjectNextStage")
	String getProjectNextStage(@HeaderMap Map<String, Object> headerMap, @QueryMap Map<String, Object> queryMap);

	/**
	 * 根据项目ID获取项目阶段id
	 * 
	 * @return projectId 参数projectId
	 */
	@RequestLine("POST /projectList/getStageIdByProjectId")
	Long getStageIdByProjectId(@HeaderMap Map<String, Object> headerMap, @QueryMap Map<String, Object> queryMap);

	/**
	 * 根据项目阶段ID查询所有的子阶段
	 * 
	 * @return projectId entityId(项目大阶段ID)
	 */
	@RequestLine("POST /projectStage/getAllChildNodeById")
	JsonResult getAllChildNodeById(@HeaderMap Map<String, Object> headerMap, @QueryMap Map<String, Object> queryMap);

	/**
	 * 根据项目阶段ID查询项目阶段的code和项目阶段的title
	 */
	@RequestLine("POST /projectStage/getCodeAndTitleById")
	Map<String, String> getCodeAndTitleById(@HeaderMap Map<String, Object> headerMap,
			@QueryMap Map<String, Object> queryMap);

	/**
	 * 获取项目状态
	 * 
	 * @param headerMap
	 * @param queryMap
	 * @RequestParam(value = "entityId") Long entityId,
	 * @return selectById.getState();
	 */
	@RequestLine("POST /projectList/getProjectState")
	Integer getProjectState(@HeaderMap Map<String, Object> headerMap, @QueryMap Map<String, Object> queryMap);

	/**
	 * 验证项目阶段
	 * 
	 * @param headerMap
	 * @param queryMap
	 * @return
	 */
	@RequestLine("POST /projectList/verifyStage")
	Boolean verifyStage(@HeaderMap Map<String, Object> headerMap, @QueryMap Map<String, Object> queryMap);

	/**
	 * 获取项目的相关参数
	 * 
	 * @param headerMap
	 * @param queryMap
	 * @return
	 */
	@RequestLine("POST /projectList/getProjectCategoryId2ById")
	Map<String, Object> getProjectCategoryId2ById(@HeaderMap Map<String, Object> headerMap, @QueryMap Map<String, Object> queryMap);

	/**
	 * 获取项目流程实例ID
	 * @param headerMap
	 * @param queryMap
	 * @return
	 */
	@RequestLine("POST /projectList/getProjectProcessInstanceId")
	String getProjectProcessInstanceId(@HeaderMap Map<String, Object> headerMap, @QueryMap Map<String, Object> queryMap);
}
