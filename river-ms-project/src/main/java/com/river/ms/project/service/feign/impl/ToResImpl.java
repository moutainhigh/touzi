package com.river.ms.project.service.feign.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.river.ms.project.service.feign.ToRes;

@Service
public class ToResImpl {

	@Autowired
	ToRes toRes;

	/**
	 * 获取重点关注的项目id result.put(f.getProjectId(), f.getProjectId());
	 */
	public Map<Long, Long> selectProjectIdsFocusonList(Map<String, Object> headerMap) {
		Map<Long, Long> selectProjectIdsFocusonList = toRes.selectProjectIdsFocusonList(headerMap);
		return selectProjectIdsFocusonList;
	}

	/**
	 * 根据用户的itcode获取用户ID
	 */
	public Long getResId(Map<String, Object> headerMap, String itCode) {
		Map<String, Object> queryMap = new HashMap<>();
		queryMap.put("itCode", itCode);
		Long resId = toRes.getResId(headerMap, queryMap);
		return resId;
	}
	
	/**
	 * 获取行业公司的一把手
	 */
	public List<Long> getComLeaderIds(Map<String, Object> headerMap, String groupCode) {
		Map<String, Object> queryMap = new HashMap<>();
		queryMap.put("groupCode", groupCode);
		Map<String, List<Long>> comLeaderIds = toRes.getComLeaderIds(headerMap, queryMap);
		List<Long> ids = (List<Long>) comLeaderIds.get("ids");
		return ids;
	}
	
	/**
	 * 获取行业公司的赋能群
	 */
	public List<Long> getFuNengQunIds(Map<String, Object> headerMap, String groupCode) {
		Map<String, Object> queryMap = new HashMap<>();
		queryMap.put("groupCode", groupCode);
		Map<String, List<Long>> comLeaderIds = toRes.getFuNengQunIds(headerMap, queryMap);
		List<Long> ids = comLeaderIds.get("ids");
		return ids;
	}
}
