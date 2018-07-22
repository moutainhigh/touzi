package com.river.ms.business.service;

import com.river.ms.business.entity.ProjectCondition;

import javax.servlet.http.HttpServletRequest;

import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 项目前置条件（赋能群确认) 服务类
 * </p>
 *
 * @author zyb
 * @since 2017-12-04
 */
public interface MPProjectConditionService extends IService<ProjectCondition> {

	/**
	 * 前置条件确认
	 * @param projectConditions
	 * @return
	 */
	Boolean insertProjectConditions(HttpServletRequest request,Long projectId);
	
}
