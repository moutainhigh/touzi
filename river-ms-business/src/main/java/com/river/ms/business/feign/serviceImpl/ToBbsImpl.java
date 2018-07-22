package com.river.ms.business.feign.serviceImpl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.river.ms.business.feign.service.ToBbs;

import feign.HeaderMap;

@Service
public class ToBbsImpl {

	@Autowired
	ToBbs toBbs;

	/**
	 * business模块调用，获取forumID（添加或者查找）
	 */
	public Long getForumIdByCode(Map<String, Object> headerMap, String entityCode, String entityTitle) {
		Map<String, Object> queryMap = new HashMap<>();
		queryMap.put("entityCode", entityCode);
		queryMap.put("entityTitle", entityTitle);
		Long forumIdByCode = toBbs.getForumIdByCode(headerMap, queryMap);
		return forumIdByCode;
	};

	/**
	 * 插入问答
	 */
	public Long insertTopicEntity(@HeaderMap Map<String, Object> headerMap, String entityTitle, Long userId,
			Long forumId, String content,String userName) {
		Map<String, Object> queryMap = new HashMap<>();
		queryMap.put("topicType", 1);
		queryMap.put("entityTitle", entityTitle);
		queryMap.put("userId", userId);
		queryMap.put("forumId", forumId);
		queryMap.put("content", content);
		queryMap.put("userName", userName);
		Long insertTopicEntity = toBbs.insertTopicEntity(headerMap, queryMap);
		return insertTopicEntity;
	}
}
