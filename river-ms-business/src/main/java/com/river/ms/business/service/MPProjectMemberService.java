package com.river.ms.business.service;

import com.river.ms.business.entity.ProjectMember;

import java.util.List;

import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 项目组成员 服务类
 * </p>
 *
 * @author zyb
 * @since 2017-12-04
 */
public interface MPProjectMemberService extends IService<ProjectMember> {

	/**
	 * 返回作为项目成员的项目id列表
	 * @param project
	 * @param resourceId
	 * @return
	 */
	List<Long> getProjectMember(Long resourceId);
	
	/**
	 * 返回作为项目成员的项目id列表,包含离开的
	 * @param project
	 * @param resourceId
	 * @return
	 */
	List<Long> getAllProjectMember(Long resourceId);
	
	/**
	 * 去重
	 * @param entitys
	 * @return
	 */
	List<ProjectMember> isExit(List<ProjectMember> entitys);
	
}
