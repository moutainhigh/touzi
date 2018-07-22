package com.river.ms.business.mapper;

import com.river.ms.business.entity.ProjectOpinionCondition;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * <p>
 * 有条件通过时的条件清单 Mapper 接口
 * </p>
 *
 * @author zyb
 * @since 2017-12-04
 */
@Mapper
public interface ProjectOpinionConditionDao extends BaseMapper<ProjectOpinionCondition> {

	List<ProjectOpinionCondition> getProjectOpinionConditionByOpinionId(@Param("opinionId") Long opinionId);
	List<ProjectOpinionCondition> getOpinionConditionByProjectId(@Param("projectId") Long projectId,@Param("type") Integer type);

}
