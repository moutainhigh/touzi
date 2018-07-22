package com.river.ms.business.feign.serviceImpl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.river.ms.business.feign.service.ToSSO;

@Service
public class ToSSOService {
	@Autowired
	ToSSO toSSO;

	/**
	 * business模块调用，获取forumID（添加或者查找）
	 */
	public String mail() {
		Map<String, Object> queryMap = new HashMap<>();
		Map<String, Object> headerMap = new HashMap<>();
		return toSSO.mail(headerMap, queryMap);
	};

}
