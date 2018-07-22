package com.river.ms.business.web;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.river.core.entity.UserEntity;
import com.river.core.result.JsonResult;
import com.river.core.utils.SessionUtils;
import com.river.ms.business.entity.ProjectApproval;
import com.river.ms.business.entity.ProjectOpinionCondition;
import com.river.ms.business.feign.serviceImpl.ToProjectImpl;
import com.river.ms.business.result.ErrorResult;
import com.river.ms.business.service.MPProjectApprovalService;
import com.river.ms.business.service.MPProjectExpertService;
import com.river.ms.business.service.MPProjectOpinionConditionService;
import com.river.ms.business.service.MPProjectTodoService;

/**
 * <p>
 * 项目审批 前端控制器
 * </p>
 *
 * @author zyb
 * @since 2017-12-04
 */
@RestController
@RequestMapping("/projectApproval")
public class ProjectApprovalAction {

	@Autowired
	MPProjectApprovalService service;

	@Autowired
	MPProjectOpinionConditionService opinionConditionService;

	@Autowired
	MPProjectTodoService projectTodoService;

	@Autowired
	ToProjectImpl toProject;

	@Autowired
	MPProjectExpertService projectExpertService;

	private Boolean isDeleteExpertToDo = true;

	/**
	 * 添加审批意见
	 * 
	 * @param request
	 * @param entity
	 * @Param expertSte 确定专家
	 * @return
	 */
	@RequestMapping(value = "/insertProjectApproval", method = RequestMethod.POST)
	public JsonResult insertProjectApproval(HttpServletRequest request, @Valid ProjectApproval entity,
			BindingResult res, @RequestParam(name = "conditions", required = false) String condition,
			@RequestParam(name = "expertSte", required = false) String expertSte,
			@RequestParam(name = "flowType", required = false) Integer flowType,
			@RequestParam(name = "projectMessage", required = false) String projectMessage,
			@RequestParam(name = "docStr", required = false) String docStr) {
		List<String> conditions = null;
		if (condition != null && !condition.equals("")) {
			String[] split = condition.split("&黄平&");
			conditions = Arrays.asList(split);
		}
		if (res.hasErrors()) {
			return JsonResult.BindingError(res);
		}
		UserEntity user = SessionUtils.getUser(request);
		if (user == null)
			return JsonResult.failure("user is null");
		Map<String, Object> headerMap = SessionUtils.getHeaderMap(request);
		Boolean verifyStage = toProject.verifyStage(headerMap, entity.getProjectId(), entity.getStage());
		if (!verifyStage) {
			return ErrorResult.PROJECT_STAGE_ERROR;
		}
		Boolean insertApproval = null;
		try {
			insertApproval = this.service.insertApproval(entity, conditions, expertSte, request, flowType,
					projectMessage, docStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JsonResult.success(insertApproval);
	}

	/**
	 * 查看项目审批结果
	 * 
	 * @param request
	 * @param projectId
	 * @param stage
	 * @return
	 */
	@RequestMapping(value = "/getProjectApproval", method = RequestMethod.POST)
	public JsonResult getProjectApproval(HttpServletRequest request, @RequestParam(value = "projectId") Long projectId,
			@RequestParam(value = "stage") String stage, @RequestParam(value = "type") Integer type) {
		List<ProjectApproval> selectByMap = this.service.getProjectApprovalByCondition(projectId, stage, type);
		return JsonResult.success(selectByMap);
	}

	/**
	 * 
	 * @param request
	 * @param projectId
	 * @param stage
	 * @return
	 */
	@RequestMapping(value = "/getProjectExpertApproval", method = RequestMethod.POST)
	public JsonResult getProjectExpertApproval(HttpServletRequest request,
			@RequestParam(value = "projectId") Long projectId,
			@RequestParam(value = "stage", required = false) Long stage) {
		if (stage != null && stage != 0) {
			Map<String, Object> headerMap = SessionUtils.getHeaderMap(request);
			Map<Long, Long> greatStage = toProject.getProjectStageNode(headerMap, stage);
			Set<Entry<Long, Long>> entrySet = greatStage.entrySet();
			for (Entry<Long, Long> entry : entrySet) {
				stage = entry.getKey();
			}
		}
		if (stage == null)
			stage = 0l;
		List<ProjectApproval> selectByMap = this.service.getProjectExpertApproval(projectId, stage);
		return JsonResult.success(selectByMap);
	}

	/**
	 * 添加项目审批(专家)
	 * 
	 * @param request
	 * @param entity
	 * @return
	 */
	@RequestMapping(value = "/insertProjectExpertApproval", method = RequestMethod.POST)
	public JsonResult insertProjectExpertApproval(HttpServletRequest request, @Valid ProjectApproval entity,
			BindingResult res, @RequestParam(name = "conditions", required = false) String condition) {

		List<String> conditions = null;
		if (condition != null && !condition.equals("")) {
			String[] split = condition.split("&黄平&");
			conditions = Arrays.asList(split);
		}
		Map<String, Object> headerMap = SessionUtils.getHeaderMap(request);

		if (res.hasErrors()) {
			return JsonResult.BindingError(res);
		}

		UserEntity user = SessionUtils.getUser(request);
		entity.setItcode(user.getEntityCode());
		entity.setResourceId(user.getResId());
		entity.setCreateTime(new Date());
		entity.setType(1);
		String projectProcessInstanceId = this.toProject.getProjectProcessInstanceId(headerMap, entity.getProjectId());
		entity.setPROCESS_INSTANCE_ID_(projectProcessInstanceId);
		boolean insert = this.service.insert(entity);
		if (this.getIsDeleteExpertToDo()) {
			this.projectTodoService.deleteTodo(entity.getProjectId(), null, user.getResId(), user.getEntityCode(), 1);
		}
		if (insert) {
			List<ProjectOpinionCondition> projectOpinionConditions = new ArrayList<>();
			if (conditions != null && !conditions.isEmpty()) {
				for (String s : conditions) {
					ProjectOpinionCondition p = new ProjectOpinionCondition();
					p.setProjectId(entity.getProjectId());
					p.setOpinionId(entity.getEntityId());
					p.setStage(entity.getStage());
					p.setCondition(s);
					p.setType(0);
					p.setCreateTime(new Date());
					projectOpinionConditions.add(p);
				}
			}
			if (projectOpinionConditions != null && !projectOpinionConditions.isEmpty()) {
				boolean insertBatch = opinionConditionService.insertBatch(projectOpinionConditions);
				if (insertBatch) {
					entity.setProjectOpinionConditions(projectOpinionConditions);
				} else {
					return ErrorResult.INSERT_PROJECT_ERROE;
				}
			}
			return JsonResult.success(entity);
		}
		return ErrorResult.INSERT_PROJECT_ERROE;
	}

	/**
	 * 添加项目审批和风险评估（专家专用）
	 * 
	 * @param request
	 * @param projectId
	 *            项目ID
	 * @param stage
	 *            项目阶段
	 * @param result
	 *            是否通过:0-拒绝1-通过2-有条件通过
	 * @param memo
	 *            审批理由
	 * @param propose
	 *            建议
	 * @param conditions
	 *            有条件通过的条件
	 * @param level
	 *            风险等级:高中低
	 * @param riskMemo
	 *            风险备注
	 * @param riskDesc
	 *            风险描述
	 * @param riskStrategy
	 *            风险策略
	 * @return
	 */
	@RequestMapping(value = "/insertProjectExpertApprovalAndRisk", method = RequestMethod.POST)
	public JsonResult insertProjectExpertApprovalAndRisk(HttpServletRequest request,
			@RequestParam(name = "projectId") Long projectId, @RequestParam(name = "stage") Long stage,
			@RequestParam(name = "result") Integer result, @RequestParam(name = "memo") String memo,
			@RequestParam(name = "propose") String propose,
			@RequestParam(name = "conditions", required = false) String condition,
			@RequestParam(name = "level") Integer level,
			@RequestParam(name = "riskMemo", required = false) String riskMemo,
			@RequestParam(name = "riskDesc", required = false) String riskDescs,
			@RequestParam(name = "riskStrategy", required = false) String riskStrategys) {
		if (projectId == null || projectId == 0 || stage == null || stage == 0 || level == null || level == 0) {
			return JsonResult.FAILURE;
		}
		List<String> conditions = null;
		if (condition != null && !condition.equals("")) {
			String[] split = condition.split("&黄平&");
			conditions = Arrays.asList(split);
		}
		List<String> riskDesc = null;
		if (riskDescs != null && !riskDescs.equals("")) {
			String[] split2 = riskDescs.split("&黄平&");
			riskDesc = Arrays.asList(split2);
		}
		List<String> riskStrategy = null;
		if (riskStrategys != null && !riskStrategys.equals("")) {
			String[] split3 = riskStrategys.split("&黄平&");
			riskStrategy = Arrays.asList(split3);
		}
		Map<String, Object> headerMap = SessionUtils.getHeaderMap(request);
		String projectProcessInstanceId = this.toProject.getProjectProcessInstanceId(headerMap, projectId);
		UserEntity user = SessionUtils.getUser(request);
		String itCode = user.getEntityCode();
		Long resId = user.getResId();
		Boolean insert = this.service.insertApprovalAndRisk(itCode, resId, projectId, stage, result, memo, propose,
				conditions, level, riskMemo, riskDesc, riskStrategy, projectProcessInstanceId);
		if (this.getIsDeleteExpertToDo() && insert) {
			this.projectTodoService.deleteTodo(projectId, null, user.getResId(), user.getEntityCode(), 1);
		}
		if (insert) {
			return JsonResult.SUCCESS;
		} else {
			return JsonResult.FAILURE;
		}
	}

	public Boolean getIsDeleteExpertToDo() {
		return isDeleteExpertToDo;
	}

}
