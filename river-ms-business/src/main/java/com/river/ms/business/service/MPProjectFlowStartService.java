package com.river.ms.business.service;

import com.river.ms.business.entity.ProjectFlowStart;

import java.util.List;

import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 启动流程配置 服务类
 * </p>
 *
 * @author zyb
 * @since 2017-12-20
 */
public interface MPProjectFlowStartService extends IService<ProjectFlowStart> {

	/**
	 * 
	 * @param groupCategoryId
	 *            项目类型ID
	 * @param type
	 *            流程类型 1-立项，2-可研，3-投决，4-投资价值评估，5，变更
	 * @return
	 */
	List<ProjectFlowStart> getProjectFlowStart(Long entityId, Long groupCategoryId, Integer type);
	
	Long isExist(Long entityId,Long flowId,Long groupCategoryId,Integer type);

	List<ProjectFlowStart> selectProjectFlowStart(Long groupCategoryId, Integer type);
}
