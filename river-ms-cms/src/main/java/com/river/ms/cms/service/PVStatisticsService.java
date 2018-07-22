package com.river.ms.cms.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.river.ms.cms.entity.CmsPVStatistics;
import com.river.ms.cms.mapper.ICmsPVStatisticsMapper;

@Service
public class PVStatisticsService {

	@Autowired
	ICmsPVStatisticsMapper mapper;

	public long insert(CmsPVStatistics record) {
		return mapper.insert(record);
	}

	public long countByArticleId(long articleId) {
		return mapper.countByArticleId(articleId);
	}

	public List<CmsPVStatistics> queryPV(CmsPVStatistics entity) {
		return mapper.queryPV(entity);
	}
	
	public long countByArticleIdIP(long articleId) {
		return mapper.countByArticleIdIp(articleId);
	}

}
