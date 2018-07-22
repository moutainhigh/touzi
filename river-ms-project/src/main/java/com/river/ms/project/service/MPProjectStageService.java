package com.river.ms.project.service;

import com.river.core.entity.RoleEntity;
import com.river.ms.project.entity.ProjectStage;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 项目阶段 服务类
 * </p>
 *
 * @author zyb
 * @since 2017-12-04
 */
public interface MPProjectStageService extends IService<ProjectStage> {

	/*
	 * 根据角色id查找项目阶段列表
	 */
	List<ProjectStage> getProjectStageByRoles(@Param("roles") List<RoleEntity> roles);

	/**
	 * 根据项目节点ID获取项目阶段
	 * 
	 * @param nodeId
	 * @return
	 */
	List<ProjectStage> getProjectStageByNode(Long nodeId);

	Long exist(Long id, String entityCode);
	/**
	 * 
	 * @param stage
	 * @return
	 */
	ProjectStage getNextStage(String stage);
}
