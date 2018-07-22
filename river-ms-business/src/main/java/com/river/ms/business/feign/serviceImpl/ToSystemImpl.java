package com.river.ms.business.feign.serviceImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.river.core.entity.UserEntity;
import com.river.ms.business.feign.service.ToSystem;

@Service
public class ToSystemImpl {

	@Autowired
	ToSystem toUser;
	
	public UserEntity getUserMessageByResId(Map<String, Object> headerMap , String entityCode) {
		Map<String, Object> queryMap = new HashMap<>();
		queryMap.put("entityCode", entityCode);
		UserEntity userMessageByResId = toUser.getUserMessageByResId(headerMap, queryMap);
		return userMessageByResId;
	}
	
	public Map<Long,String> getGongSi(Map<String, Object> headerMap,List<Long> userId){
		Map<String, Object> queryMap = new HashMap<>();
		queryMap.put("userId", userId);
		Map<Long,String> gongSi = toUser.getGongSi(headerMap, queryMap);
		return gongSi;
	}
}
