package com.river.ms.business.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.river.core.business.EnumFlowType;
import com.river.core.entity.RoleEntity;
import com.river.core.entity.UserEntity;
import com.river.core.result.JsonResult;
import com.river.core.utils.SessionUtils;
import com.river.ms.business.entity.HistoryResult;
import com.river.ms.business.entity.ProcessResult;
import com.river.ms.business.entity.ProjectDecision;
import com.river.ms.business.entity.ProjectEvaluate;
import com.river.ms.business.entity.ProjectFeasibility;
import com.river.ms.business.entity.ProjectModification;
import com.river.ms.business.entity.ProjectSetup;
import com.river.ms.business.entity.ProjectStageMessage;
import com.river.ms.business.feign.serviceImpl.ToActivitiService;
import com.river.ms.business.feign.serviceImpl.ToProjectImpl;
import com.river.ms.business.feign.serviceImpl.ToSystemImpl;
import com.river.ms.business.service.MPProjectDecisionService;
import com.river.ms.business.service.MPProjectEvaluateService;
import com.river.ms.business.service.MPProjectFeasibilityService;
import com.river.ms.business.service.MPProjectListService;
import com.river.ms.business.service.MPProjectModificationService;
import com.river.ms.business.service.MPProjectSetupService;

/**
 * <p>
 * 项目列表 前端控制器
 * </p>
 *
 * @author zyb
 * @since 2017-12-04
 */
@RestController
@RequestMapping("/ProjectListAction")
public class ProjectListAction {

	@Autowired
	MPProjectListService service;

	@Autowired
	ToSystemImpl toUser;

	@Autowired
	ToProjectImpl toProject;

	@Autowired
	ToActivitiService toActiviti;

	@Autowired
	MPProjectSetupService projectSetupService;

	@Autowired
	MPProjectModificationService projectModificationService;

	@Autowired
	MPProjectDecisionService projectDecisionService;

	@Autowired
	MPProjectFeasibilityService projectFeasibilityService;

	@Autowired
	MPProjectEvaluateService projectEvaluateService;

	/**
	 * 根据用户的角色和项目所处阶段筛选用户所能看到的项目ID
	 * 
	 * @param request
	 * @param entity
	 * @return
	 */
	@RequestMapping(value = "/getProjectList", method = RequestMethod.POST)
	public Map<Long, Long> getProjectList(HttpServletRequest request,
			@RequestParam(name = "stageCode", required = false) List<Long> stageCode,
			@RequestParam("roleCode") String roleCode) {
		Map<Long, Long> result = new HashMap<>();
		if (stageCode != null && stageCode.size() > 0 && roleCode != null && !roleCode.equals("")) {
			UserEntity user = SessionUtils.getUser(request);
			Map<String, Object> headerMap = SessionUtils.getHeaderMap(request);
			Long resourceId = user.getResId();
			result = this.service.getProjectList(headerMap, stageCode, roleCode, resourceId);
		}
		return result;
	}

	/**
	 * 根据用户的角色筛选所参与过的项目的ID
	 * 
	 * @param request
	 * @param entity
	 * @return
	 */
	@RequestMapping(value = "/getAllProjectList", method = RequestMethod.POST)
	public JsonResult getAllProjectList(HttpServletRequest request, @RequestParam("entityCode") String entityCode) {
		Map<String, Object> headerMap = SessionUtils.getHeaderMap(request);
		UserEntity user = toUser.getUserMessageByResId(headerMap, entityCode);
		List<String> roleCode = new ArrayList<>();
		for (RoleEntity r : user.getRoles()) {
			roleCode.add(r.getEntityCode());
		}
		List<Long> projectList = this.service.getProjectList(headerMap, roleCode, user.getResId(), entityCode);
		return JsonResult.success(projectList);
	}

	/**
	 * 只有专家才有
	 * 
	 * @param request
	 * @param councilType
	 * @return
	 */
	@RequestMapping(value = "/getCouncilProjectList", method = RequestMethod.POST)
	public JsonResult getCouncilProjectList(HttpServletRequest request,
			@RequestParam("councilType") String councilType) {
		UserEntity user = SessionUtils.getUser(request);
		if (user == null)
			return JsonResult.NO_JURISDICTION;
		List<String> roles = new ArrayList<>();
		for (RoleEntity r : user.getRoles()) {
			roles.add(r.getEntityCode());
		}

		// List<Long> projectList = this.service.getProjectList(headerMap,roleCode,
		// user.getResId(),entityCode);
		return JsonResult.success(null);
	}

	/**
	 * 获取项目阶段截至时间
	 * 
	 * @param request
	 * @param projectId
	 * @return
	 */
	@RequestMapping(value = "/getProjectStageTime", method = RequestMethod.POST)
	public JsonResult getProjectStageTime(HttpServletRequest request, @RequestParam("projectId") Long projectId,
			@RequestParam(name = "stage", required = false) Long stage,
			@RequestParam(name = "processInstanceId", required = false) String processInstanceId) {
		if (projectId == null || projectId == 0) {
			return JsonResult.failure("项目不正确！");
		}
		Map<String, Object> headerMap = SessionUtils.getHeaderMap(request);
		if (processInstanceId == null || processInstanceId.equals("")) {
			processInstanceId = this.toProject.getProjectProcessInstanceId(headerMap, projectId);
		}
		ProcessResult process = this.toActiviti.getProcess(headerMap, processInstanceId);
		if (process != null && process.getProcessType() != null) {
			Integer processType = process.getProcessType();
			// 立项
			if (processType.equals(EnumFlowType.SETUP.getFlowType())) {
				EntityWrapper<ProjectSetup> wrapper = new EntityWrapper<>();
				wrapper.eq("projectId", projectId);
				List<ProjectSetup> selectList = projectSetupService.selectList(wrapper);
				if (selectList != null && selectList.size() > 0) {
					return JsonResult.success(selectList.get(0).getApprovalDate());
				}
			}
			// 可研
			if (processType.equals(EnumFlowType.FEASIBILITY.getFlowType())) {
				EntityWrapper<ProjectFeasibility> wrapper = new EntityWrapper<>();
				wrapper.eq("projectId", projectId);
				List<ProjectFeasibility> selectList = projectFeasibilityService.selectList(wrapper);
				if (selectList != null && selectList.size() > 0) {
					return JsonResult.success(selectList.get(0).getApprovalDate());
				}
			}
			// 投决
			if (processType.equals(EnumFlowType.DECISION.getFlowType())) {
				EntityWrapper<ProjectDecision> wrapper = new EntityWrapper<>();
				wrapper.eq("projectId", projectId);
				List<ProjectDecision> selectList = projectDecisionService.selectList(wrapper);
				if (selectList != null && selectList.size() > 0) {
					return JsonResult.success(selectList.get(0).getApprovalDate());
				}
			}
			// 投资价值评估
			if (processType.equals(EnumFlowType.AFTER.getFlowType())) {
			}
			// 变更
			if (processType.equals(EnumFlowType.MODIFICATION.getFlowType())) {
				EntityWrapper<ProjectModification> wrapper = new EntityWrapper<>();
				wrapper.eq("projectId", projectId);
				List<ProjectModification> selectList = projectModificationService.selectList(wrapper);
				if (selectList != null && selectList.size() > 0) {
					return JsonResult.success(selectList.get(0).getApprovalDate());
				}
			}
		}
		return JsonResult.success(new Date());
	}

	/**
	 * 获取项目阶段信息
	 * 
	 * @param request
	 * @param projectId
	 * @param processInstanceId
	 * @return
	 */
	@RequestMapping(value = "/getProjectStageMessage", method = RequestMethod.POST)
	public JsonResult getProjectStageMessage(HttpServletRequest request, @RequestParam("projectId") Long projectId,
			@RequestParam(name = "processInstanceId", required = false) String processInstanceId) {
		Map<String, Object> result = new HashMap<>();
		Integer key = -1;
		Object value = new Object();
		if (projectId == null || projectId == 0) {
			return JsonResult.failure("项目不正确！");
		}
		Map<String, Object> headerMap = SessionUtils.getHeaderMap(request);
		if (processInstanceId == null || processInstanceId.equals("")) {
			processInstanceId = this.toProject.getProjectProcessInstanceId(headerMap, projectId);
		}
		ProcessResult process = this.toActiviti.getProcess(headerMap, processInstanceId);
		if (process != null && process.getProcessType() != null) {
			Integer processType = process.getProcessType();
			// 立项
			if (processType.equals(EnumFlowType.SETUP.getFlowType())) {
				key = EnumFlowType.SETUP.getFlowType();
				EntityWrapper<ProjectSetup> projectSetupWrapper = new EntityWrapper<>();
				projectSetupWrapper.eq("projectId", projectId);
				projectSetupWrapper.eq("PROCESS_INSTANCE_ID_", processInstanceId);
				List<ProjectSetup> selectList = projectSetupService.selectList(projectSetupWrapper);
				result.put("key", key);
				result.put("value", selectList);
			}
			// 可研
			if (processType.equals(EnumFlowType.FEASIBILITY.getFlowType())) {
				key = EnumFlowType.FEASIBILITY.getFlowType();
				EntityWrapper<ProjectFeasibility> projectFeasibilityWrapper = new EntityWrapper<>();
				projectFeasibilityWrapper.eq("projectId", projectId);
				projectFeasibilityWrapper.eq("PROCESS_INSTANCE_ID_", processInstanceId);
				List<ProjectFeasibility> selectList = projectFeasibilityService.selectList(projectFeasibilityWrapper);
				result.put("key", key);
				result.put("value", selectList);
			}
			// 投决
			if (processType.equals(EnumFlowType.DECISION.getFlowType())) {
				key = EnumFlowType.DECISION.getFlowType();
				EntityWrapper<ProjectDecision> projectDecisionWrapper = new EntityWrapper<>();
				projectDecisionWrapper.eq("projectId", projectId);
				projectDecisionWrapper.eq("PROCESS_INSTANCE_ID_", processInstanceId);
				List<ProjectDecision> selectList = projectDecisionService.selectList(projectDecisionWrapper);
				result.put("key", key);
				result.put("value", selectList);
			}
			// 投资价值评估
			if (processType.equals(EnumFlowType.AFTER.getFlowType())) {
				key = EnumFlowType.AFTER.getFlowType();
				EntityWrapper<ProjectEvaluate> projectEvaluateWrapper = new EntityWrapper<>();
				projectEvaluateWrapper.eq("projectId", projectId);
				projectEvaluateWrapper.eq("PROCESS_INSTANCE_ID_", processInstanceId);
				List<ProjectEvaluate> selectList = projectEvaluateService.selectList(projectEvaluateWrapper);
				result.put("key", key);
				result.put("value", selectList);
			}
			// 变更
			if (processType.equals(EnumFlowType.MODIFICATION.getFlowType())) {
				key = EnumFlowType.MODIFICATION.getFlowType();
				EntityWrapper<ProjectModification> projectModificationWrapper = new EntityWrapper<>();
				projectModificationWrapper.eq("projectId", projectId);
				projectModificationWrapper.eq("PROCESS_INSTANCE_ID_", processInstanceId);
				List<ProjectModification> selectList = projectModificationService
						.selectList(projectModificationWrapper);
				result.put("key", key);
				result.put("value", selectList);
			}
		}
		return JsonResult.success(result);
	}

	/**
	 * 获取项目阶段日志
	 * 
	 * @param request
	 * @param projectId
	 * @param processInstanceId
	 * @return
	 */
	@RequestMapping(value = "/getProjectStageLog", method = RequestMethod.POST)
	public JsonResult getProjectStageLog(HttpServletRequest request, @RequestParam("projectId") Long projectId) {
		if (projectId == null || projectId == 0) {
			return JsonResult.failure("项目不正确！");
		}
		ProjectStageMessage projectStageMessage = new ProjectStageMessage();
		Map<String, Object> headerMap = SessionUtils.getHeaderMap(request);
		// 立项
		EntityWrapper<ProjectSetup> projectSetupWrapper = new EntityWrapper<>();
		projectSetupWrapper.eq("projectId", projectId);
		List<ProjectSetup> projectSetups = projectSetupService.selectList(projectSetupWrapper);
		for (ProjectSetup projectSetup : projectSetups) {
			List<HistoryResult> history = this.toActiviti.getHistory(headerMap, projectSetup.getPROCESS_INSTANCE_ID_());
			for (HistoryResult historyResult : history) {
				if(historyResult.getTaskType() != null && historyResult.getTaskType().equals("startEvent")) {
					historyResult.setTaskTitle("立项申请");
					historyResult.setStartTime(projectSetup.getCreateTime());
					historyResult.setEndTime(projectSetup.getCreateTime());
					historyResult.setItcode(projectSetup.getItcode());
				}
			}
			this.service.getHistoryResultMessage(history, projectId,projectSetup.getPROCESS_INSTANCE_ID_());
			projectSetup.setHistoryResult(history);
		}
		projectStageMessage.setProjectSetups(projectSetups);
		// 可研
		EntityWrapper<ProjectFeasibility> projectFeasibilityWrapper = new EntityWrapper<>();
		projectFeasibilityWrapper.eq("projectId", projectId);
		List<ProjectFeasibility> projectFeasibilitys = projectFeasibilityService.selectList(projectFeasibilityWrapper);
		for (ProjectFeasibility projectFeasibility : projectFeasibilitys) {
			List<HistoryResult> history = this.toActiviti.getHistory(headerMap, projectFeasibility.getPROCESS_INSTANCE_ID_());
			for (HistoryResult historyResult : history) {
				if(historyResult.getTaskType() != null && historyResult.getTaskType().equals("startEvent")) {
					historyResult.setTaskTitle("可研申请");
					historyResult.setStartTime(projectFeasibility.getCreateTime());
					historyResult.setEndTime(projectFeasibility.getCreateTime());
					historyResult.setItcode(projectFeasibility.getItcode());
				}
			}
			this.service.getHistoryResultMessage(history, projectId,projectFeasibility.getPROCESS_INSTANCE_ID_());
			projectFeasibility.setHistoryResult(history);
		}
		projectStageMessage.setProjectFeasibilitys(projectFeasibilitys);
		// 投决
		EntityWrapper<ProjectDecision> projectDecisionWrapper = new EntityWrapper<>();
		projectDecisionWrapper.eq("projectId", projectId);
		List<ProjectDecision> projectDecisions = projectDecisionService.selectList(projectDecisionWrapper);
		for (ProjectDecision projectDecision : projectDecisions) {
			List<HistoryResult> history = this.toActiviti.getHistory(headerMap, projectDecision.getPROCESS_INSTANCE_ID_());
			for (HistoryResult historyResult : history) {
				if(historyResult.getTaskType() != null && historyResult.getTaskType().equals("startEvent")) {
					historyResult.setTaskTitle("投决申请");
					historyResult.setStartTime(projectDecision.getCreateTime());
					historyResult.setEndTime(projectDecision.getCreateTime());
					historyResult.setItcode(projectDecision.getItcode());
				}
			}
			this.service.getHistoryResultMessage(history, projectId,projectDecision.getPROCESS_INSTANCE_ID_());
			projectDecision.setHistoryResult(history);
		}
		projectStageMessage.setProjectDecisions(projectDecisions);
		// 变更
		EntityWrapper<ProjectModification> projectModificationWrapper = new EntityWrapper<>();
		projectModificationWrapper.eq("projectId", projectId);
		List<ProjectModification> projectModifications = projectModificationService.selectList(projectModificationWrapper);
		for (ProjectModification projectModification : projectModifications) {
			List<HistoryResult> history = this.toActiviti.getHistory(headerMap, projectModification.getPROCESS_INSTANCE_ID_());
			for (HistoryResult historyResult : history) {
				if(historyResult.getTaskType() != null && historyResult.getTaskType().equals("startEvent")) {
					historyResult.setTaskTitle("变更申请");
					historyResult.setStartTime(projectModification.getCreateTime());
					historyResult.setEndTime(projectModification.getCreateTime());
					historyResult.setItcode(projectModification.getItcode());
				}
			}
			this.service.getHistoryResultMessage(history, projectId,projectModification.getPROCESS_INSTANCE_ID_());
			projectModification.setHistoryResult(history);
		}
		projectStageMessage.setProjectModifications(projectModifications);
		// 投资价值评估
		EntityWrapper<ProjectEvaluate> projectEvaluateWrapper = new EntityWrapper<>();
		projectEvaluateWrapper.eq("projectId", projectId);
		List<ProjectEvaluate> projectEvaluates = projectEvaluateService.selectList(projectEvaluateWrapper);
		for (ProjectEvaluate projectEvaluate : projectEvaluates) {
			List<HistoryResult> history = this.toActiviti.getHistory(headerMap, projectEvaluate.getPROCESS_INSTANCE_ID_());
			for (HistoryResult historyResult : history) {
				if(historyResult.getTaskType() != null && historyResult.getTaskType().equals("startEvent")) {
					historyResult.setTaskTitle("投资价值评估申请");
					historyResult.setStartTime(projectEvaluate.getCreateTime());
					historyResult.setEndTime(projectEvaluate.getCreateTime());
					historyResult.setItcode(projectEvaluate.getItcode());
				}
			}
			this.service.getHistoryResultMessage(history, projectId,projectEvaluate.getPROCESS_INSTANCE_ID_());
			projectEvaluate.setHistoryResult(history);
		}
		projectStageMessage.setProjectEvaluates(projectEvaluates);
		return JsonResult.success(projectStageMessage);
	}
	
	/**
	 * 获取项目阶段信息
	 * 
	 * @param request
	 * @param projectId
	 * @param processInstanceId
	 * @return
	 */
	@RequestMapping(value = "/judgeProjectNoFollowApproval", method = RequestMethod.POST)
	public JsonResult judgeProjectNoFollowApproval(HttpServletRequest request, @RequestParam("projectId") Long projectId,
			@RequestParam(name = "processInstanceId", required = false) String processInstanceId) {
		if (projectId == null || projectId == 0) {
			return JsonResult.failure("项目不正确！");
		}
		Boolean result = false;
		Map<String, Object> headerMap = SessionUtils.getHeaderMap(request);
		if (processInstanceId == null || processInstanceId.equals("")) {
			processInstanceId = this.toProject.getProjectProcessInstanceId(headerMap, projectId);
		}
		ProcessResult process = this.toActiviti.getProcess(headerMap, processInstanceId);
		if (process != null && process.getProcessType() != null) {
			Integer processType = process.getProcessType();
			// 立项
			if (processType.equals(EnumFlowType.SETUP.getFlowType())) {
				result = false;
			}
			// 可研
			if (processType.equals(EnumFlowType.FEASIBILITY.getFlowType())) {
				result = true;
			}
			// 投决
			if (processType.equals(EnumFlowType.DECISION.getFlowType())) {
				result = true;
			}
			// 投资价值评估
			if (processType.equals(EnumFlowType.AFTER.getFlowType())) {
				result = false;
			}
			// 变更
			if (processType.equals(EnumFlowType.MODIFICATION.getFlowType())) {
				result = true;
			}
		}
		return JsonResult.success(result);
	}
}
