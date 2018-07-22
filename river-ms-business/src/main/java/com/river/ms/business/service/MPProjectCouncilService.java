package com.river.ms.business.service;

import com.river.ms.business.entity.ProjectCouncil;

import javax.servlet.http.HttpServletRequest;

import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 项目投决评议会，变更评审会 服务类
 * </p>
 *
 * @author zyb
 * @since 2017-12-04
 */
public interface MPProjectCouncilService extends IService<ProjectCouncil> {
	
	/**
	 * 保存项目投决评议会，变更评审会，以及参会人员
	 */
	void insertProjectCouncilAndMember(ProjectCouncil entity,HttpServletRequest request) throws Exception ;

}
