package com.river.ms.project.service;

import com.river.core.entity.RoleEntity;
import com.river.core.entity.UserEntity;
import com.river.ms.project.entity.ProjectNode;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 流程节点定义 服务类
 * </p>
 *
 * @author zyb
 * @since 2017-12-08
 */
public interface MPProjectNodeService extends IService<ProjectNode> {
	/*
	 * 根据角色id查找node节点
	 */
	List<ProjectNode> getProjectNodeByRoles(Map<String, Object> headerMap , List<RoleEntity> roles,UserEntity user);

	Long exist(Long id, String entityCode);

}
