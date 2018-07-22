package com.river.ms.project.service.feign.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.river.core.result.JsonResult;
import com.river.ms.project.service.feign.ToBusiness;

@Service
public class ToBusinessImpl {

	@Autowired
	ToBusiness toBusiness;

	/**
	 * 获取用户的认证信息，以及resId
	 */
	public Map<Long, Long> getProjectList(Map<String, Object> headerMap, List<Long> stageCode, String roleCode) {
		Map<String, Object> queryMap = new HashMap<>();
		queryMap.put("stageCode", stageCode);
		queryMap.put("roleCode", roleCode);
		Map<Long, Long> projectList = toBusiness.getProjectList(headerMap, queryMap);
		return projectList;
	}

	/**
	 * 获取项目关键指标统计 result.put("cost", cost); result.put("tzze", null);
	 */
	public Map<String, Object> projectKeypointStatistics(Map<String, Object> headerMap, List<Long> projectIds) {
		Map<String, Object> queryMap = new HashMap<>();
		queryMap.put("projectIds", projectIds);
		Map<String, Object> projectKeypointStatistics = toBusiness.projectKeypointStatistics(headerMap, queryMap);
		return projectKeypointStatistics;
	}

	/**
	 * 获取用户要操作的项目ID List<Long> projectIds
	 */
	public JsonResult getProjectToDo(Map<String, Object> headerMap, Long roleId) {
		Map<String, Object> queryMap = new HashMap<>();
		queryMap.put("roleId", roleId);
		JsonResult projectToDo = toBusiness.getProjectToDo(headerMap, queryMap);
		return projectToDo;
	}

	/**
	 * 添加项目用户操作信息
	 * 
	 * @param headerMap
	 * @param queryMap
	 * @return
	 */
	public JsonResult insertProjectToDo(Map<String, Object> headerMap, Map<String, Object> queryMap) {
		JsonResult insertProjectToDo = toBusiness.insertProjectToDo(headerMap, queryMap);
		return insertProjectToDo;
	}

	/**
	 * 获取专家评议过的项目ID
	 * 
	 * @param headerMap
	 * @return
	 */
	public JsonResult getProjectsCountByResId(Map<String, Object> headerMap) {
		Map<String, Object> queryMap = new HashMap<>();
		JsonResult projectsCountByResId = toBusiness.getProjectsCountByResId(headerMap, queryMap);
		return projectsCountByResId;
	}

	public Long getFlowId(Map<String, Object> headerMap, Long groupCategoryId, Integer type) {
		Map<String, Object> queryMap = new HashMap<>();
		queryMap.put("groupCategoryId", groupCategoryId);
		queryMap.put("type", type);
		Long flowId = toBusiness.getFlowId(headerMap, queryMap);
		return flowId;
	}
}
