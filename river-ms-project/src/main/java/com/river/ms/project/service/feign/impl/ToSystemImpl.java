package com.river.ms.project.service.feign.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.river.ms.project.service.feign.ToSystem;

import feign.HeaderMap;

@Service
public class ToSystemImpl {

	@Autowired
	ToSystem toSystem;

	/**
	 * 获取角色名称
	 */
	public String getRoleName(@HeaderMap Map<String, Object> headerMap, @RequestParam(value="roleId") long roleId) {
		Map<String, Object> queryMap = new HashMap<>();
		queryMap.put("roleId", roleId);
		String roleName = toSystem.getRoleName(headerMap, queryMap);
		return roleName;
	}
	
	public List<String> getOrgCodes(Map<String, Object> headerMap, List<String> orgCodes) {
		Map<String, Object> queryMap = new HashMap<>();
		queryMap.put("orgCodes", orgCodes);
		List<String> orgChildCodes = toSystem.getOrgCodes(headerMap, queryMap);
		return orgChildCodes;
	}
}
