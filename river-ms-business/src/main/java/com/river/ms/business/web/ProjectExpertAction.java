package com.river.ms.business.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.river.core.entity.UserEntity;
import com.river.core.result.JsonResult;
import com.river.core.utils.ListToString;
import com.river.core.utils.SessionUtils;
import com.river.ms.business.entity.ActivitiResult;
import com.river.ms.business.entity.ProjectApproval;
import com.river.ms.business.entity.ProjectExpert;
import com.river.ms.business.entity.ProjectOpinion;
import com.river.ms.business.feign.serviceImpl.ToActivitiService;
import com.river.ms.business.feign.serviceImpl.ToProjectImpl;
import com.river.ms.business.result.ErrorResult;
import com.river.ms.business.service.MPProjectApprovalService;
import com.river.ms.business.service.MPProjectExpertService;
import com.river.ms.business.service.MPProjectOpinionService;
import com.river.ms.business.service.MPProjectTodoService;

/**
 * <p>
 * 项目各阶段专家确定 前端控制器
 * </p>
 *
 * @author zyb
 * @since 2017-12-04
 */
@RestController
@RequestMapping("/projectExpert")
public class ProjectExpertAction {

	@Autowired
	MPProjectExpertService service;

	@Autowired
	ToProjectImpl toProject;

	@Autowired
	MPProjectTodoService projectTodoService;

	@Autowired
	ToActivitiService toActivitiService;

	@Autowired
	MPProjectApprovalService projectApprovalService;

	@Autowired
	MPProjectOpinionService projectOpinionService;

	/**
	 * 专家级风控选择
	 * 
	 * @param request
	 * @param entitys
	 * @return
	 */
	@RequestMapping(value = "/insertProjectExpert", method = RequestMethod.POST)
	public JsonResult insertProjectExpert(HttpServletRequest request, @RequestParam(name = "str") String str) {

		List<ProjectExpert> entitys = JSONArray.parseArray(str, ProjectExpert.class);
		if (entitys != null && entitys.size() > 0) {
			Long projectId = entitys.get(0).getProjectId();
			Map<String, Object> headerMap = SessionUtils.getHeaderMap(request);
			String projectProcessInstanceId = toProject.getProjectProcessInstanceId(headerMap, projectId);
			for (ProjectExpert p : entitys) {
				p.setState(0);
				p.setCreateTime(new Date());
				p.setPROCESS_INSTANCE_ID_(projectProcessInstanceId);
			}
			Boolean insert = true;
			if (entitys != null && entitys.size() > 0) {
				insert = this.service.insertProjectExpert(entitys);
			}
			if (insert) {
				return JsonResult.success(entitys);
			}
			return ErrorResult.INSERT_PROJECT_ERROE;
		} else {
			return JsonResult.SUCCESS;
		}
	}

	@RequestMapping(value = "/affirmInsertProjectExpert", method = RequestMethod.POST)
	public JsonResult affirmInsertProjectExpert(HttpServletRequest request,
			@RequestParam(name = "projectId") Long projectId) {
		UserEntity user = SessionUtils.getUser(request);
		// 完成操作
		// 获取项目节点code
		Map<String, Object> headerMap = SessionUtils.getHeaderMap(request);
		String stageCode = this.toProject.getStageByProjectId(headerMap, projectId);
		String liuchengshiliIdByProjectId = this.toProject.getLiuchengshiliIdByProjectId(headerMap, projectId);
		// 完成操作
		Map<String, Object> activitiQueryMap = new HashMap<>();
		List<String> keys = new ArrayList<>();
		keys.add("userId");
		keys.add("projectId");
		keys.add("reason");
		keys.add("taskKey");
		keys.add("processInstanceId");
		List<Object> values = new ArrayList<>();
		values.add(user.getEntityCode());
		values.add(projectId);
		values.add("");
		values.add(stageCode);
		values.add(liuchengshiliIdByProjectId);
		List<String> types = new ArrayList<>();
		types.add("S");
		types.add("S");
		types.add("S");
		types.add("S");
		types.add("S");
		activitiQueryMap.put("keys", ListToString.toString(keys));
		activitiQueryMap.put("values", ListToString.toString(values));
		activitiQueryMap.put("types", ListToString.toString(types));
		ActivitiResult nodeEnd = this.toActivitiService.nodeEnd(headerMap, activitiQueryMap);
		if (nodeEnd.getStatus() == 0) {
			// 根据项目阶段code获取项目阶段ID
			Map<Long, Long> projectStageIdByCode = toProject.getProjectStageIdByCode(headerMap,
					nodeEnd.getTasks().get(0).getTaskKey());
			// 设置项目阶段
			Boolean setProjectState = toProject.setProjectStage(headerMap, projectId,
					projectStageIdByCode.get(new Long(1)), nodeEnd.getTaskMemo());
			toProject.setProjectState(headerMap, projectId, Integer.valueOf(nodeEnd.getTaskState()));
			if (setProjectState) {
				// 添加操作用户
				this.projectTodoService.deleteTodo(projectId, null, null, null, 0);
				Map<Long, List<Long>> roleAndUserByProjectIdAndStage = this.toProject
						.getRoleAndUserByProjectIdAndStage(headerMap, projectId);
				Set<Entry<Long, List<Long>>> entrySet = roleAndUserByProjectIdAndStage.entrySet();
				for (Entry<Long, List<Long>> e : entrySet) {
					if (e.getValue() != null && e.getValue().size() > 0) {
						for (Long l : e.getValue()) {
							this.projectTodoService.insertTodo(projectId, e.getKey(), l, null, 0);
						}
					} else {
						this.projectTodoService.insertTodo(projectId, e.getKey(), null, null, 0);
					}
				}
				return JsonResult.SUCCESS;
			} else {
				return ErrorResult.ACTIVITI_ERROR;
			}
		} else {
			return ErrorResult.ACTIVITI_ERROR;
		}
	}

	/**
	 * 专家和风控确认
	 * 
	 * @param request
	 * @param entitys
	 * @return
	 */
	@RequestMapping(value = "/affirmProjectExpert", method = RequestMethod.POST)
	public JsonResult affirmProjectExpert(HttpServletRequest request, @RequestParam(name = "str") String str) {
		List<ProjectExpert> entitys = JSONArray.parseArray(str, ProjectExpert.class);
		if (entitys != null && entitys.size() > 0) {
			Map<String, Object> headerMap = SessionUtils.getHeaderMap(request);
			Long projectId = entitys.get(0).getProjectId();
			String projectProcessInstanceId = this.toProject.getProjectProcessInstanceId(headerMap, projectId);
			for (ProjectExpert p : entitys) {
				p.setState(1);
				if (p.getEntityId() == null) {
					p.setCreateTime(new Date());
					p.setPROCESS_INSTANCE_ID_(projectProcessInstanceId);
				}
			}
			Boolean insert = this.service.insertOrUpdateBatch(entitys);
			if (insert) {
				for (ProjectExpert p : entitys) {
					if (p.getRole() == 0) {
						this.projectTodoService.insertTodo(p.getProjectId(), 2L, p.getResourceId(), p.getItcode(), 1);
					} else {
						this.projectTodoService.insertTodo(p.getProjectId(), 3L, p.getResourceId(), p.getItcode(), 1);
					}
				}
				return JsonResult.SUCCESS;
			} else {
				return ErrorResult.INSERT_PROJECT_ERROE;
			}
		}
		return JsonResult.SUCCESS;
	}

	/**
	 * 获取指定的专家以及风控
	 * 
	 * @param projectId
	 * @param stage
	 * @return
	 */
	@RequestMapping(value = "/getProjectExpert", method = RequestMethod.POST)
	public JsonResult getProjectExpert(HttpServletRequest request, @RequestParam(name = "projectId") Long projectId,
			@RequestParam(name = "stage", required = false) Long stage) {
		List<ProjectExpert> selectProjectExpert = this.service.selectProjectExpert(request, projectId, stage);
		return JsonResult.success(selectProjectExpert);
	}

	/**
	 * 
	 * @param projectId
	 * @param stage
	 * @return
	 */
	@RequestMapping(value = "/getProjectAvailExpert", method = RequestMethod.POST)
	public JsonResult getProjectAvailExpert(@RequestParam(name = "projectId") Long projectId,
			@RequestParam(name = "stage", required = false) Integer stage) {
		Map<String, Object> columnMap = new HashMap<>();
		columnMap.put("projectId", projectId);
		columnMap.put("state", 1);
		if (stage != null) {
			columnMap.put("stage", stage);
		}
		List<ProjectExpert> selectByMap = this.service.selectByMap(columnMap);
		return JsonResult.success(selectByMap);
	}

	/**
	 * 获取专家评审结果统计
	 * 
	 * @param request
	 * @param projectId
	 * @param stage
	 * @return
	 */
	@RequestMapping(value = "/dataStatisticsApproval", method = RequestMethod.POST)
	public JsonResult dataStatisticsApproval(HttpServletRequest request,
			@RequestParam(name = "projectId") Long projectId) {
		Map<String, Integer> result = new HashMap<>();
		// 获取项目阶段
		Map<String, Object> headerMap = SessionUtils.getHeaderMap(request);
		Long stageId = this.toProject.getStageIdByProjectId(headerMap, projectId);
		Long stage = new Long(0);
		List<Long> childNodeIdList = new ArrayList<>();
		if (stageId != null && stageId != 0) {
			// 获取项目大阶段
			Map<Long, Long> parentStageId = this.toProject.getProjectStageNode(headerMap, stageId);
			Set<Entry<Long, Long>> entrySet = parentStageId.entrySet();
			for (Entry<Long, Long> entry : entrySet) {
				stage = entry.getKey();
			}
			JsonResult allChildNodeById = this.toProject.getAllChildNodeById(headerMap, stage);
			JsonResult childNodeIds = this.toProject.getAllChildNodeById(headerMap, stage);
			childNodeIdList = (List<Long>) childNodeIds.getData();
		}
		// 获取项目目前阶段的选中的专家
		Map<String, Object> columnMap = new HashMap<>();
		columnMap.put("projectId", projectId);
		if (stage != null && stage != 0) {
			columnMap.put("stage", stage);
		}
		List<ProjectExpert> projectExpert = this.service.selectByMap(columnMap);
		// 获取专家ID
		List<Long> expertId = new ArrayList<>();
		for (ProjectExpert p : projectExpert) {
			expertId.add(p.getResourceId());
		}
		EntityWrapper<ProjectApproval> approvalWrapper = new EntityWrapper<>();
		approvalWrapper.in("resourceId", expertId);
		approvalWrapper.eq("projectId", projectId);
		if (childNodeIdList != null && childNodeIdList.size() > 0) {
			approvalWrapper.in("stage", childNodeIdList);
		}
		List<ProjectApproval> projectApprovals = this.projectApprovalService.selectList(approvalWrapper);
		// 统计
		// 评审过
		int y = 0;
		// 没有评审过
		int n = 0;
		// 通过
		int tg = 0;
		// 不通过
		int btg = 0;
		// 有条件通过
		int tjtg = 0;
		for (ProjectExpert pe : projectExpert) {
			Boolean b = false;
			for (ProjectApproval ap : projectApprovals) {
				if (pe.getResourceId().equals(ap.getResourceId())) {
					b = true;
					Integer result2 = ap.getResult();
					if (result2 != null) {
						if (result2.equals(-1) || result2.equals(0)) {
							btg++;
						}
						if (result2.equals(2)) {
							tg++;
						}
						if (result2.equals(3)) {
							tjtg++;
						}
					}
				}
			}
			if (b) {
				y++;
			} else {
				n++;
			}
		}
		result.put("y", y);
		result.put("n", n);
		result.put("tg", tg);
		result.put("btg", btg);
		result.put("tjtg", tjtg);
		return JsonResult.success(result);
	}

	/**
	 * 获取专家评议结果统计
	 * 
	 * @param request
	 * @param projectId
	 * @param stage
	 * @return
	 */
	@RequestMapping(value = "/dataStatisticsOpinion", method = RequestMethod.POST)
	public JsonResult dataStatisticsOpinion(HttpServletRequest request,
			@RequestParam(name = "projectId") Long projectId) {
		Map<String, Integer> result = new HashMap<>();
		// 获取项目阶段
		Map<String, Object> headerMap = SessionUtils.getHeaderMap(request);
		Long stageId = this.toProject.getStageIdByProjectId(headerMap, projectId);
		// 获取项目大阶段
		Map<Long, Long> parentStageId = this.toProject.getProjectStageNode(headerMap, stageId);
		Set<Entry<Long, Long>> entrySet = parentStageId.entrySet();
		Long stage = new Long(0);
		for (Entry<Long, Long> entry : entrySet) {
			stage = entry.getKey();
		}
		// 获取项目目前阶段的选中的专家
		Map<String, Object> columnMap = new HashMap<>();
		columnMap.put("projectId", projectId);
		columnMap.put("stage", stage);
		List<ProjectExpert> projectExpert = this.service.selectByMap(columnMap);
		// 获取专家ID
		List<Long> expertId = new ArrayList<>();
		for (ProjectExpert p : projectExpert) {
			expertId.add(p.getResourceId());
		}
		EntityWrapper<ProjectOpinion> opinionWrapper = new EntityWrapper<>();
		opinionWrapper.in("resourceId", expertId);
		opinionWrapper.eq("projectId", projectId);
		opinionWrapper.eq("stage", stageId);
		List<ProjectOpinion> projectOpinions = this.projectOpinionService.selectList(opinionWrapper);
		// 统计
		int y = 0;
		int n = 0;
		for (ProjectExpert pe : projectExpert) {
			Boolean b = false;
			for (ProjectOpinion ap : projectOpinions) {
				if (pe.getResourceId().equals(ap.getResourceId())) {
					b = true;
				}
			}
			if (b) {
				y++;
			} else {
				n++;
			}
		}
		result.put("y", y);
		result.put("n", n);
		return JsonResult.success(result);
	}

	/**
	 * 移除指定的专家以及风控
	 * 
	 * @param projectId
	 * @param stage
	 * @return
	 */
	@RequestMapping(value = "/removeProjectExpert", method = RequestMethod.POST)
	public JsonResult removeProjectExpert(@RequestParam(name = "entityId") Long entityId) {
		boolean deleteById = this.service.deleteById(entityId);
		return JsonResult.success(deleteById);
	}

	/**
	 * 评议项目个数
	 * 
	 * @param resId
	 * @return
	 */
	@RequestMapping(value = "/getProjectsCountByResId", method = RequestMethod.POST)
	public JsonResult getProjectsCountByResId(HttpServletRequest request,
			@RequestParam(name = "resId", required = false) Long resId) {
		if (resId == null) {
			resId = SessionUtils.getUser(request).getResId();
		}
		List<Long> allProjects = this.service.getAllProjects(resId);
		return JsonResult.success(allProjects);
	}
}
