package com.river.ms.business.service;

import com.river.core.entity.UserEntity;
import com.river.ms.business.entity.ProjectApproval;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 项目审批 服务类
 * </p>
 *
 * @author zyb
 * @since 2017-12-04
 */
public interface MPProjectApprovalService extends IService<ProjectApproval> {

	/**
	 * 
	 * @param projectId
	 * @param stage
	 * @return
	 */
	List<ProjectApproval> getProjectApprovalByCondition(Long projectId, String stage, Integer type);

	/**
	 * 审批结束，处理节点
	 * 
	 * @param request
	 * @param entity
	 * @return
	 */
	Boolean activitiEnd(HttpServletRequest request, ProjectApproval entity);

	Boolean insertApproval(ProjectApproval entity, List<String> conditions, String expertSte,
			HttpServletRequest request,Integer flowType,String projectMessage,String docStr) throws Exception;

	Boolean insertApprovalAndRisk(String itCode, Long resId, Long projectId, Long stage, Integer result, String memo,
			String propose, List<String> conditions, Integer level, String riskMemo, List<String> riskDesc,
			List<String> riskStrategy,String projectProcessInstanceId);
	List<ProjectApproval> getProjectExpertApproval(Long projectId, Long stage);

}
