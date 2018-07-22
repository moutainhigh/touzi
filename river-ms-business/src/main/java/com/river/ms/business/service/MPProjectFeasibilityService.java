package com.river.ms.business.service;

import com.river.core.entity.UserEntity;
import com.river.ms.business.entity.ProjectFeasibility;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 项目可研 服务类
 * </p>
 *
 * @author zyb
 * @since 2017-12-04
 */
public interface MPProjectFeasibilityService extends IService<ProjectFeasibility> {
	
	/**
	 * 添加项目可研
	 * 
	 * @param entity
	 * @return
	 */
	Boolean insertProjectFeasibility(ProjectFeasibility entity, Map<String, Object> headerMap,String str,String docStr,UserEntity user);

	/**
	 * 设置项目可研状态
	 * 
	 * @param entityId
	 * @param state
	 * @param headerMap
	 * @return
	 */
	Boolean setProjectFeasibilityState(Long entityId, Integer state, Map<String, Object> headerMap);
	
	/**
	 * 打回的可研资料上传
	 * @param projectId
	 * @param headerMap
	 * @return
	 */
	Boolean updateProjectFeasibilityState(HttpServletRequest request, ProjectFeasibility entity,String docStr);
}
