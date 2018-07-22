package com.river.ms.business.service;

import com.river.core.entity.UserEntity;
import com.river.ms.business.entity.ProjectSetup;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 项目立项申请 服务类
 * </p>
 *
 * @author zyb
 * @since 2017-12-04
 */
public interface MPProjectSetupService extends IService<ProjectSetup> {

	/**
	 * 项目立项
	 * @param project
	 * @param headerMap
	 * @return
	 */
	Boolean insertProjectSetup(ProjectSetup project,Map<String, Object> headerMap,String str,String docStr,UserEntity user);
	
	/**
	 * 更改项目立项状态
	 * @param projectId
	 * @param state
	 * @return
	 */
	Boolean setProjectSetupState(Long projectId,Integer state,Map<String, Object> headerMap);
	
	/**
	 * 打回的立项资料上传
	 * @param projectId
	 * @param headerMap
	 * @return
	 */
	Boolean updateProjectSetup(HttpServletRequest request,ProjectSetup entity,String docStr);
}
