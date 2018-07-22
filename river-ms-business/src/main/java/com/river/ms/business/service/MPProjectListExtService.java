package com.river.ms.business.service;

import com.river.ms.business.entity.DataPie;
import com.river.ms.business.entity.ProjectListExt;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 项目信息扩展表（数据） 服务类
 * </p>
 *
 * @author zyb
 * @since 2017-12-04
 */
public interface MPProjectListExtService extends IService<ProjectListExt> {
	DataPie getInvestStatistics(Integer year,Integer type,String categoryId);
	DataPie getCountStatistics(Integer year,Integer type,String categoryId);
	List<Map<String,Object>> getCountStatisticsBy(Integer year,Integer type, String categoryId);
	List<Map<String,Object>> getCompany(Integer year);
	List<Map<String,Object>> getProjectType(Integer year,String groupCode);
	List<Map<String,Object>> countMytodo(String itcode);
	List<Map<String,Object>> computeGroup(Integer year);
}
