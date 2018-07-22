package com.river.ms.project.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.river.core.entity.RoleEntity;
import com.river.ms.project.entity.ProjectStage;

/**
 * <p>
 * 项目阶段 Mapper 接口
 * </p>
 *
 * @author zyb
 * @since 2017-12-04
 */
@Mapper
public interface ProjectStageDao extends BaseMapper<ProjectStage> {
	
	/*
	 * 根据角色id查找项目阶段列表
	 */
	List<ProjectStage> getProjectStageByRoles(@Param("roles") List<RoleEntity> roles);
	
	Long exist(@Param("id")Long id, @Param("entityCode")String entityCode);
	/**
	 * 
	 * @param stage
	 * @return
	 */
	ProjectStage getNextStage(@Param("stage")String stage);
}
