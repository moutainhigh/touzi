package com.river.ms.business.mapper;

import com.river.ms.business.entity.ProjectRisk;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * <p>
 * 风险评议 Mapper 接口
 * </p>
 *
 * @author zyb
 * @since 2017-12-20
 */
@Mapper
public interface ProjectRiskDao extends BaseMapper<ProjectRisk> {
	List<ProjectRisk> selectProjectRisk(@Param(value = "projectId") Long projectId,
			@Param(value = "stage") Long stage);
	
	List<ProjectRisk> selectProjectRiskByProjectId(@Param(value = "projectId") Long projectId,
			@Param(value = "resourceId") Long resourceId);
	
	List<ProjectRisk> selectProjectRiskByProjectIdAll(@Param(value = "projectId") Long projectId,
			@Param(value = "resourceId") Long resourceId,@Param("stage") Long stage,@Param("processInstanceId")String processInstanceId);
}
