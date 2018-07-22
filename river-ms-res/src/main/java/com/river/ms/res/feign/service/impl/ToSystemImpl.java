package com.river.ms.res.feign.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.river.ms.res.entity.ResList;
import com.river.ms.res.feign.service.ToSystem;

import feign.HeaderMap;
import feign.QueryMap;

@Service
public class ToSystemImpl {

	@Autowired
	ToSystem toUser;
	
	public Boolean codeIsExit(Map<String, Object> headerMap,Long entityId,String entityCode) {
		Map<String, Object> queryMap = new HashMap<>();
		queryMap.put("entityId", entityId);
		queryMap.put("entityCode", entityCode);
		Boolean codeIsExit = toUser.codeIsExit(headerMap, queryMap);
		return codeIsExit;
	}
	
	public Long insertUser(Map<String, Object> headerMap,ResList resEntity, Long employeeId,String eId) {
		Map<String, Object> queryMap = new HashMap<>();
		queryMap.put("entityCode", resEntity.getItcode());
		queryMap.put("entityTitle", resEntity.getEntityTitle());
		if (resEntity.getEntityDesc() != null) {
			queryMap.put("entityDesc", resEntity.getEntityDesc());
		}
		if (employeeId != null && employeeId != -1) {
			queryMap.put("employeeId", employeeId);
		}
		if(eId != null) {
			queryMap.put("entityGUID", eId);
		}
		Long codeIsExit = toUser.insertUser(headerMap, queryMap);
		return codeIsExit;
	}
	
	public Map<Long,String> getGongSi(Map<String, Object> headerMap,List<Long> userId){
		Map<String, Object> queryMap = new HashMap<>();
		queryMap.put("userId", userId);
		Map<Long,String> gongSi = toUser.getGongSi(headerMap, queryMap);
		return gongSi;
	}
}
