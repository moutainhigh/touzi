package com.river.ms.business.service;

import com.river.ms.business.entity.ProjectMilestone;

import feign.Param;

import java.util.List;

import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 项目节点 服务类
 * </p>
 *
 * @author zyb
 * @since 2017-12-04
 */
public interface MPProjectMilestoneService extends IService<ProjectMilestone> {

	/**
	 * 更改项目节点状态
	 * @param entityId
	 * @param state
	 * @return
	 */
	Boolean updateProjectMilestoneState(Long entityId, Integer state);
	
	/**
	 * 根据项目id查找节点列表
	 */
	List<ProjectMilestone> getProjectMilestoneListByProjectId(Long projectId);
	
	Long exist(Long entityId, Long projectId, String milestone);

}
