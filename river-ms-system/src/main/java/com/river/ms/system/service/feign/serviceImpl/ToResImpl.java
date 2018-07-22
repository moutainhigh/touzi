package com.river.ms.system.service.feign.serviceImpl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.river.ms.system.service.feign.ToRes;

@Service
public class ToResImpl {

	@Autowired
	private ToRes toRes;

	/**
	 * 获取用户的认证信息，以及resId
	 */
	public Map<String, String> getResAuthentication(Map<String, Object> headerMap, Long userId, String itcode) {
		Map<String, Object> queryMap = new HashMap<>();
		queryMap.put("userId", userId);
		queryMap.put("itcode", itcode);
		Map<String, String> resAuthentication = this.toRes.getResAuthentication(headerMap, queryMap);
		return resAuthentication;
	}
}
