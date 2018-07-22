package com.river.ms.cms.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.river.ms.cms.entity.CmsPVStatistics;

@Mapper
public interface ICmsPVStatisticsMapper {

	long insert(@Param("entity") CmsPVStatistics record);

	long countByArticleId(@Param("articleId") long articleId);

	List<CmsPVStatistics> queryPV(@Param("entity") CmsPVStatistics entity);

	long countByArticleIdIp(@Param("articleId") long articleId);
	
}
