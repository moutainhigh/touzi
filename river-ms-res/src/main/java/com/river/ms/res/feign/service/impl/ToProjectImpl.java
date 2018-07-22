package com.river.ms.res.feign.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.river.ms.res.feign.service.ToProject;

@Service
public class ToProjectImpl {

	@Autowired
	private ToProject ToProject;

	/**
	 * 获取项目阶段的大阶段 result.put(selectById.getEntityId(), selectById.getEntityId());
	 */
	public Map<Long, Long> getProjectStageNode(Map<String, Object> headerMap, Long stageCode) {
		Map<String, Object> queryMap = new HashMap<>();
		queryMap.put("stageCode", stageCode);
		Map<Long, Long> projectStageNode = ToProject.getProjectStageNode(headerMap, queryMap);
		return projectStageNode;
	}

	/**
	 * 根据项目ID获取项目阶段id
	 */
	public Long getStageIdByProjectId(Map<String, Object> headerMap, Long projectId) {
		Map<String, Object> queryMap = new HashMap<>();
		queryMap.put("projectId", projectId);
		Long stageIdByProjectId = ToProject.getStageIdByProjectId(headerMap, queryMap);
		return stageIdByProjectId;
	}
}
