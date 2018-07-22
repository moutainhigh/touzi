package com.river.ms.business.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.river.core.business.EnumFlowType;
import com.river.core.business.EnumRole;
import com.river.ms.business.entity.HistoryResult;
import com.river.ms.business.entity.ProjectDecision;
import com.river.ms.business.entity.ProjectEvaluate;
import com.river.ms.business.entity.ProjectFeasibility;
import com.river.ms.business.entity.ProjectModification;
import com.river.ms.business.entity.ProjectSetup;
import com.river.ms.business.feign.serviceImpl.ToProjectImpl;
import com.river.ms.business.mapper.ProjectListDao;
import com.river.ms.business.service.MPProjectDecisionService;
import com.river.ms.business.service.MPProjectEvaluateService;
import com.river.ms.business.service.MPProjectExpertService;
import com.river.ms.business.service.MPProjectFeasibilityService;
import com.river.ms.business.service.MPProjectListService;
import com.river.ms.business.service.MPProjectMemberService;
import com.river.ms.business.service.MPProjectModificationService;
import com.river.ms.business.service.MPProjectSetupService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 项目列表 服务实现类
 * </p>
 *
 * @author zyb
 * @since 2017-12-08
 */
@Service
public class ProjectListServiceDiy implements MPProjectListService {

	@Autowired
	MPProjectExpertService projectExpertService;

	@Autowired
	MPProjectMemberService projectMemberService;

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

	@Autowired
	ToProjectImpl toProject;

	@Autowired
	ProjectListDao projectListDao;

	@Override
	public Map<Long, Long> getProjectList(Map<String, Object> headerMap, List<Long> stageCode, String roleCode,
			Long resourceId) {
		Map<Long, Long> result = new HashMap<>();
		if (roleCode != null) {
			if (roleCode.equals("ZHUXI")) {
				result = toProject.getProjectIds(headerMap, stageCode, roleCode);
			}
			if (roleCode.equals("ZHUANJIA")) {
				Map<Long, Long> projectExpert = projectExpertService.getProjectExpert(headerMap, stageCode, resourceId,
						(long) 0);
				result = projectExpert;
			}
			if (roleCode.equals("YIBASHOU")) {
				result = toProject.getProjectIds(headerMap, stageCode, roleCode);
			}
			if (roleCode.equals("YEWURENYUAN")) {
				List<Long> projectMember = projectMemberService.getProjectMember(resourceId);
				result = this.getMap(projectMember);
			}
			if (roleCode.equals("XIANGMUJINGLI")) {
				result = toProject.getProjectIds(headerMap, stageCode, roleCode);
			}
			if (roleCode.equals("FUNENGQUN")) {
				result = toProject.getProjectIds(headerMap, stageCode, roleCode);
			}
			if (roleCode.equals("FENGKONG")) {
				Map<Long, Long> projectExpert = projectExpertService.getProjectExpert(headerMap, stageCode, resourceId,
						(long) 1);
				result = projectExpert;
			}
			if (roleCode.equals("CFO")) {
				result = toProject.getProjectIds(headerMap, stageCode, roleCode);
			}
			if (roleCode.equals("CEO")) {
				result = toProject.getProjectIds(headerMap, stageCode, roleCode);
			}
		}
		return result;
	}

	private Map<Long, Long> getMap(List<Long> ids) {
		Map<Long, Long> result = new HashMap<>();
		for (Long l : ids) {
			result.put(l, null);
		}
		return result;

	}

	@Override
	public List<Long> getProjectList(Map<String, Object> headerMap, List<String> roleCode, Long resourceId,
			String itCode) {
		Set<Long> ids = new HashSet<>();
		List<Long> result = new ArrayList<>();
		for (String role : roleCode) {
			if (role.equals(EnumRole.Chairman.getRole())) {
				Map<Long, Long> projectIdsByUserIdAndUserRoles = toProject.getProjectIdsByUserIdAndUserRoles(headerMap,
						role, itCode, resourceId);
				this.getSet(projectIdsByUserIdAndUserRoles, ids);
			}
			if (role.equals(EnumRole.Expert.getRole())) {
				List<Long> projectExpert = projectExpertService.getAllProjects(resourceId);
				this.getSet(projectExpert, ids);
			}
			if (role.equals(EnumRole.Groupleader.getRole())) {
				Map<Long, Long> projectIdsByUserIdAndUserRoles = toProject.getProjectIdsByUserIdAndUserRoles(headerMap,
						role, itCode, resourceId);
				this.getSet(projectIdsByUserIdAndUserRoles, ids);
			}
			if (role.equals(EnumRole.Member.getRole())) {
				List<Long> projectMember = projectMemberService.getAllProjectMember(resourceId);
				this.getSet(projectMember, ids);
			}
			if (role.equals(EnumRole.Teameader.getRole())) {
				Map<Long, Long> projectIdsByUserIdAndUserRoles = toProject.getProjectIdsByUserIdAndUserRoles(headerMap,
						role, itCode, resourceId);
				this.getSet(projectIdsByUserIdAndUserRoles, ids);
			}
			if (role.equals(EnumRole.Thinktank.getRole())) {
				Map<Long, Long> projectIdsByUserIdAndUserRoles = toProject.getProjectIdsByUserIdAndUserRoles(headerMap,
						role, itCode, resourceId);
				this.getSet(projectIdsByUserIdAndUserRoles, ids);
			}
			if (role.equals(EnumRole.RiskExpert.getRole())) {
				List<Long> projectExpert = projectExpertService.getAllProjects(resourceId);
				this.getSet(projectExpert, ids);
			}
			if (role.equals(EnumRole.CFO.getRole())) {
				Map<Long, Long> projectIdsByUserIdAndUserRoles = toProject.getProjectIdsByUserIdAndUserRoles(headerMap,
						role, itCode, resourceId);
				this.getSet(projectIdsByUserIdAndUserRoles, ids);
			}
			if (role.equals(EnumRole.CEO.getRole())) {
				Map<Long, Long> projectIdsByUserIdAndUserRoles = toProject.getProjectIdsByUserIdAndUserRoles(headerMap,
						role, itCode, resourceId);
				this.getSet(projectIdsByUserIdAndUserRoles, ids);
			}
		}
		for (Long l : ids) {
			result.add(l);
		}
		return result;
	}

	public void getSet(List<Long> list, Set<Long> ids) {
		for (Long l : list) {
			ids.add(l);
		}
	}

	public void getSet(Map<Long, Long> map, Set<Long> ids) {
		Set<Entry<Long, Long>> entrySet = map.entrySet();
		for (Entry<Long, Long> e : entrySet) {
			ids.add(e.getKey());
		}
	}

	@Override
	public Boolean updateProjectMessage(Integer flowType, String projectMessage) {
		Boolean result = true;
		// 立项
		if (flowType.equals(EnumFlowType.SETUP.getFlowType())) {
			ProjectSetup projectSetup = JSONObject.parseObject(projectMessage, ProjectSetup.class);
			result = projectSetupService.updateById(projectSetup);
		}
		// 可研
		if (flowType.equals(EnumFlowType.FEASIBILITY.getFlowType())) {
			ProjectFeasibility projectFeasibility = JSONObject.parseObject(projectMessage, ProjectFeasibility.class);
			result = projectFeasibilityService.updateById(projectFeasibility);
		}
		// 投决
		if (flowType.equals(EnumFlowType.DECISION.getFlowType())) {
			ProjectDecision projectDecision = JSONObject.parseObject(projectMessage, ProjectDecision.class);
			result = projectDecisionService.updateById(projectDecision);
		}
		// 投资价值评估
		if (flowType.equals(EnumFlowType.AFTER.getFlowType())) {
			ProjectEvaluate projectEvaluate = JSONObject.parseObject(projectMessage, ProjectEvaluate.class);
			result = projectEvaluateService.updateById(projectEvaluate);
		}
		// 变更
		if (flowType.equals(EnumFlowType.MODIFICATION.getFlowType())) {
			ProjectModification projectModification = JSONObject.parseObject(projectMessage, ProjectModification.class);
			result = projectModificationService.updateById(projectModification);
		}
		return result;
	}

	@Override
	public void getHistoryResultMessage(List<HistoryResult> history, Long projectId,String processInstanceId) {
		// 获取用户的itcode
		List<String> itcode = this.getItcode(history);
		// 获取stagecode
		List<String> stageCode = this.getStageCode(history);
		if (itcode != null && itcode.size() > 0 && stageCode != null && stageCode.size() > 0) {
			// 获取用户的姓名
			List<HistoryResult> resName = this.projectListDao.getResName(itcode);
			// 获取stageid
			List<HistoryResult> stageId = this.projectListDao.getStageId(stageCode);
			for (HistoryResult historyResult : history) {
				// 设置姓名
				String userName = new String();
				Long resId = new Long(0);
				for (HistoryResult h : resName) {
					if (h.getItcode().equals(historyResult.getItcode())) {
						userName = h.getUserName();
						resId = h.getResId();
						break;
					}
				}
				historyResult.setUserName(userName);
				historyResult.setResId(resId);
				//设置stageid
				Long stageid = new Long(0);
				for (HistoryResult h : stageId) {
					if (h.getTaskKey().equals(historyResult.getTaskKey())) {
						stageid = h.getStageId();
						break;
					}
				}
				historyResult.setStageId(stageid);
			}
			List<Long> stageIds = this.getStageId(history);
			if (stageIds != null && stageIds.size() > 0) {
				List<HistoryResult> stageDocNum = this.projectListDao.getStageDocNum(stageIds, projectId);
				for (HistoryResult historyResult : history) {
					if (historyResult.getTaskType() != null && historyResult.getTaskType().equals("startEvent")) {
						List<HistoryResult> stageDocNum2 = this.projectListDao.getBigStageDocNum(processInstanceId, projectId);
						if(stageDocNum2 != null && stageDocNum2.size()>0 && stageDocNum2.get(0).getDocNum() != null) {
							historyResult.setDocNum(stageDocNum2.get(0).getDocNum());
						}
					}else {
						Integer douNum = new Integer(0);
						for (HistoryResult h : stageDocNum) {
							if (h.getStageId().equals(historyResult.getStageId())) {
								douNum = h.getDocNum();
								break;
							}
						}
						historyResult.setDocNum(douNum);
					}
				}
			}
		}
	}

	// 获取阶段ID
	private List<Long> getStageId(List<HistoryResult> history) {
		List<Long> result = new ArrayList<>();
		for (HistoryResult historyResult : history) {
			if (historyResult.getTaskType() != null && !historyResult.getTaskType().equals("startEvent")
					&& !historyResult.getTaskType().equals("endEvent")) {
				Long stageId = historyResult.getStageId();
				if (stageId != null && stageId != 0) {
					result.add(stageId);
				}
			}
		}
		return result;
	}

	// 获取操作对象的itcode
	private List<String> getItcode(List<HistoryResult> history) {
		List<String> result = new ArrayList<>();
		for (HistoryResult historyResult : history) {
			if (historyResult.getTaskType() != null 
					&& !historyResult.getTaskType().equals("endEvent")) {
				String itcode = historyResult.getItcode();
				if (itcode != null && !itcode.equals("")) {
					result.add(itcode);
				}
			}
		}
		return result;
	}

	// 获取节点code
	private List<String> getStageCode(List<HistoryResult> history) {
		List<String> result = new ArrayList<>();
		for (HistoryResult historyResult : history) {
			if (historyResult.getTaskType() != null && !historyResult.getTaskType().equals("startEvent")
					&& !historyResult.getTaskType().equals("endEvent")) {
				String taskKey = historyResult.getTaskKey();
				if (taskKey != null && !taskKey.equals("")) {
					result.add(taskKey);
				}
			}
		}
		return result;
	}
}
