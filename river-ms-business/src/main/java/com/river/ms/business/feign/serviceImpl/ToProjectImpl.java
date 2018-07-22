package com.river.ms.business.feign.serviceImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.river.core.result.JsonResult;
import com.river.ms.business.feign.service.ToProject;

@Service
public class ToProjectImpl {

	@Autowired
	ToProject toProject;

	/**
	 * 设置项目状态
	 */
	public Boolean setProjectState(Map<String, Object> headerMap, Long projectId, Integer state) {
		Map<String, Object> queryMap = new HashMap<>();
		queryMap.put("entityId", projectId);
		queryMap.put("state", state);
		Boolean setProjectState = toProject.setProjectState(headerMap, queryMap);
		return setProjectState;
	};

	/**
	 * 获取项目状态
	 */
	public Integer getProjectState(Map<String, Object> headerMap, Long projectId) {
		Map<String, Object> queryMap = new HashMap<>();
		queryMap.put("entityId", projectId);
		Integer setProjectState = toProject.getProjectState(headerMap, queryMap);
		return setProjectState;
	};

	/**
	 * 设置项目阶段
	 */
	public Boolean setProjectStage(Map<String, Object> headerMap, Long projectId, Long stageCode,String stageName) {
		Map<String, Object> queryMap = new HashMap<>();
		queryMap.put("entityId", projectId);
		queryMap.put("stageCode", stageCode);
		if (stageName != null && !stageName.equals("")) {
			queryMap.put("stageName", stageName);
		}
		Boolean setProjectStage = toProject.setProjectStage(headerMap, queryMap);
		return setProjectStage;
	}

	/**
	 * 根据项目阶段ID查找项目ID result.put(projectList.getEntityId(), null);
	 */
	public Map<Long, Long> getProjectIds(Map<String, Object> headerMap, List<Long> stageCode, String roleCode) {
		Map<String, Object> queryMap = new HashMap<>();
		queryMap.put("stageCode", stageCode);
		queryMap.put("roleCode", roleCode);
		Map<Long, Long> projectIds = toProject.getProjectIds(headerMap, queryMap);
		return projectIds;
	}

	/**
	 * 根据用户ID和用户角色获取项目ID result.put(projectList.getEntityId(),
	 * projectList.getEntityId());
	 */
	public Map<Long, Long> getProjectIdsByUserIdAndUserRoles(Map<String, Object> headerMap, String role, String itCode,
			Long resId) {
		Map<String, Object> queryMap = new HashMap<>();
		queryMap.put("role", role);
		queryMap.put("itCode", itCode);
		queryMap.put("resId", resId);
		Map<Long, Long> projectIdsByUserIdAndUserRoles = toProject.getProjectIdsByUserIdAndUserRoles(headerMap,
				queryMap);
		return projectIdsByUserIdAndUserRoles;
	}

	/**
	 * 获取项目阶段的大阶段（根据项目阶段ID） result.put(selectById.getEntityId(),
	 * selectById.getEntityId());
	 */
	public Map<Long, Long> getProjectStageNode(Map<String, Object> headerMap, Long stageCode) {
		Map<String, Object> queryMap = new HashMap<>();
		queryMap.put("stageCode", stageCode);
		Map<Long, Long> projectStageNode = toProject.getProjectStageNode(headerMap, queryMap);
		return projectStageNode;
	}

	/**
	 * 根据项目阶段code获取项目阶段ID result.put(new Long(1), selectByMap.get(0).getEntityId());
	 */
	public Map<Long, Long> getProjectStageIdByCode(Map<String, Object> headerMap, String stageCode) {
		Map<String, Object> queryMap = new HashMap<>();
		queryMap.put("stageCode", stageCode);
		Map<Long, Long> projectStageIdByCode = toProject.getProjectStageIdByCode(headerMap, queryMap);
		return projectStageIdByCode;
	}

	/**
	 * 根据项目ID和项目阶段获取可操作角色和用户 Map<Long, List<Long>> users
	 */
	public Map<Long, List<Long>> getRoleAndUserByProjectIdAndStage(Map<String, Object> headerMap, Long projectId) {
		Map<String, Object> queryMap = new HashMap<>();
		queryMap.put("projectId", projectId);
		Map<Long, List<Long>> roleAndUserByProjectIdAndStage = toProject.getRoleAndUserByProjectIdAndStage(headerMap,
				queryMap);
		return roleAndUserByProjectIdAndStage;
	}

	/**
	 * 根据项目ID获取项目阶段code stage.getEntityCode();
	 */
	public String getStageByProjectId(Map<String, Object> headerMap, Long projectId) {
		Map<String, Object> queryMap = new HashMap<>();
		queryMap.put("projectId", projectId);
		String stageByProjectId = toProject.getStageByProjectId(headerMap, queryMap);
		return stageByProjectId;
	}

	/**
	 * 设置流程实例ID
	 */
	public Boolean setLiuchengshiliIdByProjectId(Map<String, Object> headerMap, Long projectId,
			String liuchengshiliId) {
		Map<String, Object> queryMap = new HashMap<>();
		queryMap.put("projectId", projectId);
		if (liuchengshiliId != null && !liuchengshiliId.equals("")) {
			queryMap.put("liuchengshiliId", liuchengshiliId);
		}
		Boolean setLiuchengshiliIdByProjectId = toProject.setLiuchengshiliIdByProjectId(headerMap, queryMap);
		return setLiuchengshiliIdByProjectId;
	}

	/**
	 * 获取流程实例ID
	 */
	public String getLiuchengshiliIdByProjectId(Map<String, Object> headerMap, Long projectId) {
		Map<String, Object> queryMap = new HashMap<>();
		queryMap.put("projectId", projectId);
		String liuchengshiliIdByProjectId = toProject.getLiuchengshiliIdByProjectId(headerMap, queryMap);
		return liuchengshiliIdByProjectId;
	}

	/**
	 * 根据项目阶段code获取下一个阶段的code
	 */
	public String getProjectNextStage(Map<String, Object> headerMap, String stageCode) {
		Map<String, Object> queryMap = new HashMap<>();
		queryMap.put("stageCode", stageCode);
		String projectNextStage = toProject.getProjectNextStage(headerMap, queryMap);
		return projectNextStage;
	}

	/**
	 * 根据项目ID获取项目阶段id
	 */
	public Long getStageIdByProjectId(Map<String, Object> headerMap, Long projectId) {
		Map<String, Object> queryMap = new HashMap<>();
		queryMap.put("projectId", projectId);
		Long stageIdByProjectId = toProject.getStageIdByProjectId(headerMap, queryMap);
		return stageIdByProjectId;
	}

	/**
	 * 根据项目阶段ID查询所有的子阶段
	 */
	public JsonResult getAllChildNodeById(Map<String, Object> headerMap, Long stageId) {
		Map<String, Object> queryMap = new HashMap<>();
		queryMap.put("entityId", stageId);
		JsonResult allChildNodeById = toProject.getAllChildNodeById(headerMap, queryMap);
		return allChildNodeById;
	}

	/**
	 * 根据项目阶段ID查询项目阶段的code和项目阶段的title result.put(selectById.getEntityCode(),
	 * selectById.getEntityTitle());
	 */
	public Map<String, String> getCodeAndTitleById(Map<String, Object> headerMap, Long entityId) {
		Map<String, Object> queryMap = new HashMap<>();
		queryMap.put("entityId", entityId);
		Map<String, String> codeAndTitleById = toProject.getCodeAndTitleById(headerMap, queryMap);
		return codeAndTitleById;
	}

	/**
	 * 验证项目阶段
	 * 
	 * @param headerMap
	 * @param projectId
	 * @param state
	 * @return
	 */
	public Boolean verifyStage(Map<String, Object> headerMap, Long projectId, Long stage) {
		Map<String, Object> queryMap = new HashMap<>();
		queryMap.put("entityId", projectId);
		queryMap.put("stage", stage);
		Boolean result = toProject.verifyStage(headerMap, queryMap);
		return result;
	};

	/**
	 * 获取项目的相关参数
	 */
	public Map<String, Object> getProjectCategoryId2ById(Map<String, Object> headerMap, Long projectId) {
		Map<String, Object> queryMap = new HashMap<>();
		queryMap.put("entityId", projectId);
		Map<String, Object> projectCategoryId2ById = toProject.getProjectCategoryId2ById(headerMap, queryMap);
		return projectCategoryId2ById;
	};
	
	/**
	 * 获取流程实例ID
	 * @param headerMap
	 * @param projectId
	 * @return
	 */
	public String getProjectProcessInstanceId(Map<String, Object> headerMap, Long projectId) {
		Map<String, Object> queryMap = new HashMap<>();
		queryMap.put("projectId", projectId);
		String projectProcessInstanceId = this.toProject.getProjectProcessInstanceId(headerMap, queryMap);
		return projectProcessInstanceId;
	}
}
