package com.river.ms.business.service;

import com.river.core.entity.UserEntity;
import com.river.ms.business.entity.ProjectModification;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 项目变更 服务类
 * </p>
 *
 * @author zyb
 * @since 2017-12-04
 */
public interface MPProjectModificationService extends IService<ProjectModification> {

	/**
	 * 项目变更申请
	 * 
	 * @param project
	 * @param headerMap
	 * @return
	 */
	Boolean insertProjectModification(ProjectModification project, Map<String, Object> headerMap, String str,String docStr,UserEntity user);

	/**
	 * 改变项目变更状态
	 * 
	 * @param projectId
	 * @param state
	 * @return
	 */
	Boolean setProjectModificationState(Long projectId, Integer state, Map<String, Object> headerMap);

	/**
	 * 打回的项目变更资料上传
	 * 
	 * @param projectId
	 * @param headerMap
	 * @return
	 */
	Boolean updateProjectModification(HttpServletRequest request, ProjectModification entity,String docStr);

	/**
	 * 获取项目变更信息
	 * 
	 * @param projectId
	 * @return
	 */
	List<ProjectModification> getProjectModificationByProjectId(Long projectId,String processInstanceId);
}
