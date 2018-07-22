package com.river.ms.project.mapper;

import com.river.core.entity.RoleEntity;
import com.river.ms.project.entity.ProjectNode;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * <p>
 * 流程节点定义 Mapper 接口
 * </p>
 *
 * @author zyb
 * @since 2017-12-08
 */
@Mapper
public interface ProjectNodeDao extends BaseMapper<ProjectNode> {
	
	/*
	 * 根据角色id查找node节点
	 */
	List<ProjectNode> getProjectNodeByRoles(@Param("roles") List<RoleEntity> roles);

	/**
	 * 根据單個角色id查找node节点
	 * @param role
	 * @return
	 */
	List<ProjectNode> getProjectNodeByRole(@Param("role") Long role);

	Long exist(@Param("id")Long id, @Param("entityCode")String entityCode);
	
}
