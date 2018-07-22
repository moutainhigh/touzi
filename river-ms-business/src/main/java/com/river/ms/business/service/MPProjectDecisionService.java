package com.river.ms.business.service;

import com.river.core.entity.UserEntity;
import com.river.ms.business.entity.ProjectDecision;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 项目投决 服务类
 * </p>
 *
 * @author zyb
 * @since 2017-12-04
 */
public interface MPProjectDecisionService extends IService<ProjectDecision> {

	/**
	 * 添加项目投决
	 * 
	 * @param entity
	 * @return
	 */
	Boolean insertProjectDecision(ProjectDecision entity, Map<String, Object> headerMap, String str, String docStr,
			UserEntity user, String partner, String partnerMember);

	/**
	 * 设置项目投决状态
	 * 
	 * @param entityId
	 * @param state
	 * @param headerMap
	 * @return
	 */
	Boolean setProjectDecisionState(Long entityId, Integer state, Map<String, Object> headerMap);

	/**
	 * 打回的立项资料上传
	 * 
	 * @param projectId
	 * @param headerMap
	 * @return
	 */
	Boolean updateProjectDecision(HttpServletRequest request, ProjectDecision entity, String docStr);

}
