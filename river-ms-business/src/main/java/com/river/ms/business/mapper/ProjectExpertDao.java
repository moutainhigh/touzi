package com.river.ms.business.mapper;

import com.river.ms.business.entity.ProjectExpert;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * <p>
 * 项目各阶段专家确定 Mapper 接口
 * </p>
 *
 * @author zyb
 * @since 2017-12-04
 */
@Mapper
public interface ProjectExpertDao extends BaseMapper<ProjectExpert> {
	
	List<Long> getAllProjectIds(@Param("resourceId") Long resourceId);

	List<ProjectExpert> selectProjectExpert(@Param("projectId") Long projectId, @Param("stage") Long stage);

}
