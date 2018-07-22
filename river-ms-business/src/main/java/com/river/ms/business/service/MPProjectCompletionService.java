package com.river.ms.business.service;

import com.river.ms.business.entity.ProjectCompletion;

import java.util.Map;

import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 项目竣工/结项申请 服务类
 * </p>
 *
 * @author zyb
 * @since 2017-12-04
 */
public interface MPProjectCompletionService extends IService<ProjectCompletion> {

	/**
	 * 添加竣工结项申请
	 * 
	 * @param entity
	 * @return
	 */
	Boolean insertProjectCompletion(ProjectCompletion entity, Map<String, Object> headerMap);

	/**
	 * 设置项目竣工结项申请状态
	 * 
	 * @param entityId
	 * @param state
	 * @param headerMap
	 * @return
	 */
	Boolean setProjectCompletionState(Long entityId, Integer state, Map<String, Object> headerMap);

}
