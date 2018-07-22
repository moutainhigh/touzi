package com.river.ms.project.service;

import com.river.ms.project.entity.ProjectNodeRole;

import java.util.List;

import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 流程节点角色 服务类
 * </p>
 *
 * @author zyb
 * @since 2017-12-08
 */
public interface MPProjectNodeRoleService extends IService<ProjectNodeRole> {

	List<ProjectNodeRole> exist(Long nodeId, List<Long> roleIds);
}
