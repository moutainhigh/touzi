package com.river.ms.business.mapper;

import com.river.ms.business.entity.ProjectListExt;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * <p>
 * 项目信息扩展表（数据） Mapper 接口
 * </p>
 *
 * @author zyb
 * @since 2017-12-04
 */
@Mapper
public interface ProjectListExtDao extends BaseMapper<ProjectListExt> {
	List<Map<String,Object>> getInvestStatistics(HashMap<String, Object> map);
	List<Map<String,Object>> getCountStatistics(HashMap<String, Object> map);
	List<Map<String,Object>> getProjectType(HashMap<String, Object> map);
	List<Map<String,Object>> getCompany(HashMap<String, Object> map);
	List<Map<String,Object>> countMytodo(HashMap<String, Object> map);
	List<Map<String,Object>> computeGroup(HashMap<String, Object> map);
}
