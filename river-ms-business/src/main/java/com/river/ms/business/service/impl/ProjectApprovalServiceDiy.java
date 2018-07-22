package com.river.ms.business.service.impl;

import com.river.core.entity.RoleEntity;
import com.river.core.entity.UserEntity;
import com.river.core.result.JsonResult;
import com.river.core.utils.ListToString;
import com.river.core.utils.SessionUtils;
import com.river.ms.business.entity.ActivitiResult;
import com.river.ms.business.entity.ProjectApproval;
import com.river.ms.business.entity.ProjectDecision;
import com.river.ms.business.entity.ProjectDocument;
import com.river.ms.business.entity.ProjectExpert;
import com.river.ms.business.entity.ProjectModification;
import com.river.ms.business.entity.ProjectOpinionCondition;
import com.river.ms.business.entity.ProjectRisk;
import com.river.ms.business.entity.ProjectRiskItem;
import com.river.ms.business.feign.serviceImpl.ToActivitiService;
import com.river.ms.business.feign.serviceImpl.ToProjectImpl;
import com.river.ms.business.mapper.ProjectApprovalDao;
import com.river.ms.business.result.ErrorResult;
import com.river.ms.business.service.MPProjectApprovalService;
import com.river.ms.business.service.MPProjectDecisionService;
import com.river.ms.business.service.MPProjectDocumentService;
import com.river.ms.business.service.MPProjectExpertService;
import com.river.ms.business.service.MPProjectModificationService;
import com.river.ms.business.service.MPProjectOpinionConditionService;
import com.river.ms.business.service.MPProjectRiskItemService;
import com.river.ms.business.service.MPProjectRiskService;
import com.river.ms.business.service.MPProjectTodoService;
import com.river.ms.business.utils.ResultCode;
import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.river.ms.business.service.MPProjectListService;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 项目审批 服务实现类
 * </p>
 *
 * @author zyb
 * @since 2017-12-04
 */
@Service
// @Transactional
public class ProjectApprovalServiceDiy extends ServiceImpl<ProjectApprovalDao, ProjectApproval>
		implements MPProjectApprovalService {

	@Autowired
	ToProjectImpl toProject;

	@Autowired
	ToActivitiService toActivitiService;

	@Autowired
	MPProjectTodoService projectTodoService;

	@Autowired
	MPProjectOpinionConditionService opinionConditionService;

	@Autowired
	MPProjectExpertService projectExpertService;

	@Autowired
	MPProjectDecisionService projectDecisionService;

	@Autowired
	MPProjectModificationService projectModificationService;

	@Autowired
	MPProjectRiskService riskService;

	@Autowired
	MPProjectRiskItemService riskItemService;

	@Autowired
	MPProjectListService projectListService;

	@Autowired
	MPProjectDocumentService projectDocumentService;

	@Override
	public List<ProjectApproval> getProjectApprovalByCondition(Long projectId, String stage, Integer type) {
		return this.baseMapper.getProjectApprovalByCondition(projectId, stage, type);
	}

	@Override
	public Boolean activitiEnd(HttpServletRequest request, ProjectApproval entity) {
		UserEntity user = SessionUtils.getUser(request);
		List<RoleEntity> roles = user.getRoles();
		// 完成操作
		// 获取项目节点code,获取请求头信息，用于feign传参数，共享session使用
		Map<String, Object> headerMap = SessionUtils.getHeaderMap(request);
		String stageCode = this.toProject.getStageByProjectId(headerMap, entity.getProjectId());

		// 临时方法
		Integer decisionMode = 1;
		if (stageCode.equals("fnTJ001") || stageCode.equals("fnTJ003")) {
			EntityWrapper<ProjectDecision> wrapper = new EntityWrapper<>();
			wrapper.eq("projectId", entity.getProjectId());
			ProjectDecision selectOne = projectDecisionService.selectOne(wrapper);
			if (selectOne != null && selectOne.getDecisionMode() != null) {
				decisionMode = selectOne.getDecisionMode();
			}
		}
		if (stageCode.equals("fnBG003") || stageCode.equals("fnBG001")) {
			EntityWrapper<ProjectModification> wrapper = new EntityWrapper<>();
			wrapper.eq("projectId", entity.getProjectId());
			ProjectModification selectOne = projectModificationService.selectOne(wrapper);
			if (selectOne != null && selectOne.getEntityType() != null) {
				decisionMode = selectOne.getEntityType();
			}
		}

		String processInstanceId = this.toProject.getLiuchengshiliIdByProjectId(headerMap, entity.getProjectId());
		System.out.println("流程实例ID:" + processInstanceId);
		if (processInstanceId == null || processInstanceId.equals("null") || processInstanceId.isEmpty()) {
			System.out.println("流程实例ID为空");
			return false;
		}
		Map<Long, List<Long>> roleAndUser = this.toProject.getRoleAndUserByProjectIdAndStage(headerMap,
				entity.getProjectId());
		Map<String, Object> activitiQueryMap = this.getactivitiQueryMap(roles, entity, user, stageCode,
				processInstanceId, roleAndUser, decisionMode);
		if (activitiQueryMap == null) {
			System.out.println("没有参数需要传递：activitiQueryMap is null");
		}
		ActivitiResult nodeEnd = this.toActivitiService.nodeEnd(headerMap, activitiQueryMap);

		entity.setPROCESS_INSTANCE_ID_(nodeEnd.getProcessInstanceId());
		if (nodeEnd.getStatus() == 0) {
			/*
			 * System.out.println("返回的状态值："+nodeEnd.getStatus());
			 * System.out.println(nodeEnd.getTaskMemo());
			 */
			if (nodeEnd.getTasks() == null) {
				// String projectNextStage =
				// toProject.getProjectNextStage(headerMap,
				// stageCode);
				// 根据项目阶段code获取项目阶段ID
				/*
				 * Map<Long, Long> projectStageIdByCode =
				 * toProject.getProjectStageIdByCode(headerMap,
				 * projectNextStage);
				 */
				// 将项目的流程实例ID置为null
				this.toProject.setLiuchengshiliIdByProjectId(headerMap, entity.getProjectId(), null);
				Boolean setProjectState2 = toProject.setProjectState(headerMap, entity.getProjectId(),
						Integer.valueOf(nodeEnd.getTaskState()));
				// 设置项目阶段
				Boolean setProjectState = toProject.setProjectStage(headerMap, entity.getProjectId(), 0l,
						nodeEnd.getTaskMemo());
				// 添加操作用户
				this.projectTodoService.deleteTodo(entity.getProjectId(), null, null, null, null);
				/*
				 * Map<Long, List<Long>> roleAndUserByProjectIdAndStage =
				 * this.toProject .getRoleAndUserByProjectIdAndStage(headerMap,
				 * entity.getProjectId()); Set<Entry<Long, List<Long>>> entrySet
				 * = roleAndUserByProjectIdAndStage.entrySet(); for (Entry<Long,
				 * List<Long>> e : entrySet) { if (e.getValue() != null &&
				 * e.getValue().size() > 0) { for (Long l : e.getValue()) {
				 * this.projectTodoService.insertTodo(entity.getProjectId(),
				 * e.getKey(), l, null, 0); } } else {
				 * this.projectTodoService.insertTodo(entity.getProjectId(),
				 * e.getKey(), null, null, 0); } }
				 */
				return true;
			} else {
				String taskCode = nodeEnd.getTasks().get(0).getTaskKey();
				// 根据项目阶段code获取项目阶段ID
				Map<Long, Long> projectStageIdByCode = toProject.getProjectStageIdByCode(headerMap, taskCode);
				// 设置项目阶段
				Boolean setProjectState = toProject.setProjectStage(headerMap, entity.getProjectId(),
						projectStageIdByCode.get(1l), nodeEnd.getTaskMemo());
				toProject.setProjectState(headerMap, entity.getProjectId(), Integer.valueOf(nodeEnd.getTaskState()));
				if (setProjectState) {
					// 添加操作用户
					this.projectTodoService.deleteTodo(entity.getProjectId(), null, null, null, 0);
					// 添加待办事项 huangping
					this.projectTodoService.insertTodos(entity.getProjectId(), taskCode);
					/*
					 * Map<Long, List<Long>> roleAndUserByProjectIdAndStage =
					 * this.toProject
					 * .getRoleAndUserByProjectIdAndStage(headerMap,
					 * entity.getProjectId()); Set<Entry<Long, List<Long>>>
					 * entrySet = roleAndUserByProjectIdAndStage.entrySet(); for
					 * (Entry<Long, List<Long>> e : entrySet) { if (e.getValue()
					 * != null && e.getValue().size() > 0) { for (Long l :
					 * e.getValue()) {
					 * this.projectTodoService.insertTodo(entity.getProjectId(),
					 * e.getKey(), l, null, 0); } } else {
					 * this.projectTodoService.insertTodo(entity.getProjectId(),
					 * e.getKey(), null, null, 0); } }
					 */
					return true;
				} else {
					return false;
				}
			}
		} else {
			return false;
		}
	}

	private Map<String, Object> getactivitiQueryMap(List<RoleEntity> roles, ProjectApproval entity, UserEntity user,
			String stageCode, String liuchengshiliIdByProjectId, Map<Long, List<Long>> roleAndUser,
			Integer decisionMode) {
		Map<String, Object> result = new HashMap<>();
		List<String> keys = new ArrayList<>();
		List<Object> values = new ArrayList<>();
		List<String> types = new ArrayList<>();
		Set<Entry<Long, List<Long>>> entrySet = roleAndUser.entrySet();
		for (Entry<Long, List<Long>> entry : entrySet) {
			for (RoleEntity r : roles) {
				if (r.getEntityId().equals(entry.getKey())) {
					List<Long> value = entry.getValue();
					if (value != null && value.size() > 0) {
						for (Long l : value) {
							if (l.equals(user.getResId())) {
								String role = r.getEntityCode();
								if (role.equals("ZHUXI")) {
									keys.add("isPass");
									keys.add("userId");
									keys.add("projectId");
									keys.add("reason");
									keys.add("taskKey");
									keys.add("processInstanceId");
									values.add(ResultCode.getReslt(entity.getResult()));
									values.add(user.getEntityCode());
									values.add(entity.getProjectId());
									values.add("");
									values.add(stageCode);
									values.add(liuchengshiliIdByProjectId);
									types.add("I");
									types.add("S");
									types.add("S");
									types.add("S");
									types.add("S");
									types.add("S");
								}
								if (role.equals("ZHUANJIA")) {
								}
								if (role.equals("YIBASHOU")) {
									keys.add("isPass");
									keys.add("userId");
									keys.add("projectId");
									keys.add("reason");
									keys.add("taskKey");
									keys.add("processInstanceId");
									values.add(ResultCode.getReslt(entity.getResult()));
									values.add(user.getEntityCode());
									values.add(entity.getProjectId());
									values.add("");
									values.add(stageCode);
									values.add(liuchengshiliIdByProjectId);
									types.add("I");
									types.add("S");
									types.add("S");
									types.add("S");
									types.add("S");
									types.add("S");
								}
								if (role.equals("YEWURENYUAN")) {
								}
								if (role.equals("XIANGMUJINGLI")) {
								}
								if (role.equals("FUNENGQUN")) {
									keys.add("isPass");
									keys.add("userId");
									keys.add("projectId");
									keys.add("reason");
									keys.add("taskKey");
									keys.add("processInstanceId");
									values.add(ResultCode.getReslt(entity.getResult()));
									values.add(user.getEntityCode());
									values.add(entity.getProjectId());
									values.add("");
									values.add(stageCode);
									values.add(liuchengshiliIdByProjectId);
									types.add("I");
									types.add("S");
									types.add("S");
									types.add("S");
									types.add("S");
									types.add("S");
								}
								if (role.equals("FENGKONG")) {
								}
								if (role.equals("CFO")) {
									keys.add("isPass");
									keys.add("userId");
									keys.add("projectId");
									keys.add("reason");
									keys.add("taskKey");
									keys.add("processInstanceId");
									values.add(ResultCode.getReslt(entity.getResult()));
									values.add(user.getEntityCode());
									values.add(entity.getProjectId());
									values.add("");
									values.add(stageCode);
									values.add(liuchengshiliIdByProjectId);
									types.add("I");
									types.add("S");
									types.add("S");
									types.add("S");
									types.add("S");
									types.add("S");
								}
								if (role.equals("CEO")) {
									keys.add("isPass");
									keys.add("userId");
									keys.add("projectId");
									keys.add("reason");
									keys.add("taskKey");
									keys.add("processInstanceId");
									values.add(ResultCode.getReslt(entity.getResult()));
									values.add(user.getEntityCode());
									values.add(entity.getProjectId());
									values.add("");
									values.add(stageCode);
									values.add(liuchengshiliIdByProjectId);
									types.add("I");
									types.add("S");
									types.add("S");
									types.add("S");
									types.add("S");
									types.add("S");
								}
								if (role.equals("ZONGCAI")) {
									keys.add("isPass");
									keys.add("userId");
									keys.add("projectId");
									keys.add("reason");
									keys.add("taskKey");
									keys.add("processInstanceId");
									values.add(ResultCode.getReslt(entity.getResult()));
									values.add(user.getEntityCode());
									values.add(entity.getProjectId());
									values.add("");
									values.add(stageCode);
									values.add(liuchengshiliIdByProjectId);
									types.add("I");
									types.add("S");
									types.add("S");
									types.add("S");
									types.add("S");
									types.add("S");
								}
							}
						}
					} else {
						String role = r.getEntityCode();
						if (role.equals("ZHUXI")) {
							keys.add("isPass");
							keys.add("userId");
							keys.add("projectId");
							keys.add("reason");
							keys.add("taskKey");
							keys.add("processInstanceId");
							values.add(ResultCode.getReslt(entity.getResult()));
							values.add(user.getEntityCode());
							values.add(entity.getProjectId());
							values.add("");
							values.add(stageCode);
							values.add(liuchengshiliIdByProjectId);
							types.add("I");
							types.add("S");
							types.add("S");
							types.add("S");
							types.add("S");
							types.add("S");
						}
						if (role.equals("ZHUANJIA")) {
						}
						if (role.equals("YIBASHOU")) {
							keys.add("isPass");
							keys.add("userId");
							keys.add("projectId");
							keys.add("reason");
							keys.add("taskKey");
							keys.add("processInstanceId");
							values.add(ResultCode.getReslt(entity.getResult()));
							values.add(user.getEntityCode());
							values.add(entity.getProjectId());
							values.add("");
							values.add(stageCode);
							values.add(liuchengshiliIdByProjectId);
							types.add("I");
							types.add("S");
							types.add("S");
							types.add("S");
							types.add("S");
							types.add("S");
						}
						if (role.equals("YEWURENYUAN")) {
						}
						if (role.equals("XIANGMUJINGLI")) {
						}
						if (role.equals("FUNENGQUN")) {
							keys.add("isPass");
							keys.add("userId");
							keys.add("projectId");
							keys.add("reason");
							keys.add("taskKey");
							keys.add("processInstanceId");
							values.add(ResultCode.getReslt(entity.getResult()));
							values.add(user.getEntityCode());
							values.add(entity.getProjectId());
							values.add("");
							values.add(stageCode);
							values.add(liuchengshiliIdByProjectId);
							types.add("I");
							types.add("S");
							types.add("S");
							types.add("S");
							types.add("S");
							types.add("S");
						}
						if (role.equals("FENGKONG")) {
						}
						if (role.equals("CFO")) {
							keys.add("isPass");
							keys.add("userId");
							keys.add("projectId");
							keys.add("reason");
							keys.add("taskKey");
							keys.add("processInstanceId");
							values.add(ResultCode.getReslt(entity.getResult()));
							values.add(user.getEntityCode());
							values.add(entity.getProjectId());
							values.add("");
							values.add(stageCode);
							values.add(liuchengshiliIdByProjectId);
							types.add("I");
							types.add("S");
							types.add("S");
							types.add("S");
							types.add("S");
							types.add("S");
						}
						if (role.equals("CEO")) {
							keys.add("isPass");
							keys.add("userId");
							keys.add("projectId");
							keys.add("reason");
							keys.add("taskKey");
							keys.add("processInstanceId");
							values.add(ResultCode.getReslt(entity.getResult()));
							values.add(user.getEntityCode());
							values.add(entity.getProjectId());
							values.add("");
							values.add(stageCode);
							values.add(liuchengshiliIdByProjectId);
							types.add("I");
							types.add("S");
							types.add("S");
							types.add("S");
							types.add("S");
							types.add("S");
						}
						if (role.equals("ZONGCAI")) {
							keys.add("isPass");
							keys.add("userId");
							keys.add("projectId");
							keys.add("reason");
							keys.add("taskKey");
							keys.add("processInstanceId");
							values.add(ResultCode.getReslt(entity.getResult()));
							values.add(user.getEntityCode());
							values.add(entity.getProjectId());
							values.add("");
							values.add(stageCode);
							values.add(liuchengshiliIdByProjectId);
							types.add("I");
							types.add("S");
							types.add("S");
							types.add("S");
							types.add("S");
							types.add("S");
						}
					}

				}
			}
		}
		if (decisionMode != null) {
			keys.add("iReport");
			values.add(decisionMode);
			types.add("I");
		}
		result.put("keys", ListToString.toString(keys));
		result.put("values", ListToString.toString(values));
		result.put("types", ListToString.toString(types));
		System.out.println(ListToString.toString(keys));
		System.out.println(ListToString.toString(values));
		System.out.println(ListToString.toString(types));
		return result;
	}

	@Override
	public Boolean insertApproval(ProjectApproval entity, List<String> conditions, String expertSte,
			HttpServletRequest request, Integer flowType, String projectMessage, String docStr) throws Exception {
		UserEntity user = SessionUtils.getUser(request);
		entity.setItcode(user.getEntityCode());
		entity.setResourceId(user.getResId());
		entity.setCreateTime(new Date());
		entity.setType(0);
		boolean insert = this.insert(entity);
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
					throw new Exception("添加前置条件出错！");
				}
			}
			if (docStr != null && !docStr.equals("")) {
				this.insertProjectDocument(docStr, request);
			}
			Boolean activitiEnd = this.activitiEnd(request, entity);
			if (activitiEnd) {
				boolean updateById = this.updateById(entity);
				if (updateById) {
					if (expertSte != null && !expertSte.equals("")) {
						Boolean insertProjectExpert = this.insertProjectExpert(expertSte,
								entity.getPROCESS_INSTANCE_ID_());
					}
					if (flowType != null && !flowType.equals(-1) && projectMessage != null
							&& !projectMessage.equals("")) {
						Boolean updateProjectMessage = projectListService.updateProjectMessage(flowType,
								projectMessage);
					}
				} else {
					throw new Exception("保存失败:updateById");
				}
				return true;
			} else {
				throw new Exception("保存失败：流程处理失败");
			}
		}
		throw new Exception("添加数据失败！");
	}

	public void insertProjectDocument(String docStr, HttpServletRequest request) {
		List<ProjectDocument> entitys = JSONArray.parseArray(docStr, ProjectDocument.class);
		Map<String, Object> headerMap = SessionUtils.getHeaderMap(request);
		UserEntity user = SessionUtils.getUser(request);
		if (entitys != null && entitys.size() > 0) {
			Long projectId = entitys.get(0).getProjectId();
			Long stageId = toProject.getStageIdByProjectId(headerMap, projectId);
			String projectProcessInstanceId = toProject.getProjectProcessInstanceId(headerMap, projectId);
			Long greatStageId = new Long(0);
			if (stageId != null && stageId != 0) {
				Map<Long, Long> greatStage = toProject.getProjectStageNode(headerMap, stageId);
				Set<Entry<Long, Long>> entrySet = greatStage.entrySet();
				for (Entry<Long, Long> entry : entrySet) {
					greatStageId = entry.getKey();
				}
			}
			for (ProjectDocument entity : entitys) {
				entity.setItcode(user.getEntityCode());
				entity.setResourceId(user.getResId());
				entity.setResName(user.getEntityTitle());
				entity.setCreateTime(new Date());
				entity.setGreatStage(greatStageId);
				entity.setStage(stageId);
				entity.setPROCESS_INSTANCE_ID_(projectProcessInstanceId);
			}
			this.projectDocumentService.insertBatch(entitys);
		}
	}

	/**
	 * 增加专家
	 * 
	 * @param expertSte
	 * @param processInstanceId
	 * @return
	 * @throws Exception
	 */
	public Boolean insertProjectExpert(String expertSte, String processInstanceId) throws Exception {
		List<ProjectExpert> entitys = JSONArray.parseArray(expertSte, ProjectExpert.class);
		if (entitys != null && entitys.size() > 0) {
			for (ProjectExpert p : entitys) {
				p.setState(1);
				if (p.getEntityId() == null) {
					p.setCreateTime(new Date());
					p.setPROCESS_INSTANCE_ID_(processInstanceId);
				}
			}
			Boolean insert = this.projectExpertService.insertOrUpdateBatch(entitys);
			if (insert) {
				for (ProjectExpert p : entitys) {
					if (p.getRole() == 0) {
						Boolean exit = this.projectTodoService.isExit(p.getProjectId(), 2L, p.getResourceId(), null, 1);
						if (!exit) {
							this.projectTodoService.insertTodo(p.getProjectId(), 2L, p.getResourceId(), p.getItcode(),
									1);
						}
					} else {
						Boolean exit = this.projectTodoService.isExit(p.getProjectId(), 3L, p.getResourceId(), null, 1);
						if (!exit) {
							this.projectTodoService.insertTodo(p.getProjectId(), 3L, p.getResourceId(), p.getItcode(),
									1);
						}
					}
				}
				return true;
			} else {
				throw new Exception("添加数据失败！");
			}
		}
		return true;
	}

	@Override
	public Boolean insertApprovalAndRisk(String itCode, Long resId, Long projectId, Long stage, Integer result,
			String memo, String propose, List<String> conditions, Integer level, String riskMemo, List<String> riskDesc,
			List<String> riskStrategy, String projectProcessInstanceId) {
		Date date = new Date();
		// 添加项目评审
		ProjectApproval projectApproval = new ProjectApproval();
		projectApproval.setProjectId(projectId);
		projectApproval.setStage(stage);
		projectApproval.setResult(result);
		projectApproval.setMemo(memo);
		projectApproval.setPropose(propose);
		projectApproval.setItcode(itCode);
		projectApproval.setResourceId(resId);
		projectApproval.setCreateTime(date);
		projectApproval.setType(1);
		projectApproval.setPROCESS_INSTANCE_ID_(projectProcessInstanceId);
		boolean insertProjectApproval = this.insert(projectApproval);
		if (conditions != null && conditions.size() > 0) {
			// 添加有条件通过的条件
			Long projectApprovalId = projectApproval.getEntityId();
			List<ProjectOpinionCondition> projectOpinionConditions = new ArrayList<>();
			for (String condition : conditions) {
				ProjectOpinionCondition projectOpinionCondition = new ProjectOpinionCondition();
				projectOpinionCondition.setProjectId(projectId);
				projectOpinionCondition.setOpinionId(projectApprovalId);
				projectOpinionCondition.setStage(stage);
				projectOpinionCondition.setCondition(condition);
				projectOpinionCondition.setType(0);
				projectOpinionCondition.setCreateTime(date);
				projectOpinionConditions.add(projectOpinionCondition);
			}
			this.opinionConditionService.insertBatch(projectOpinionConditions);
		}
		// 添加风险评议
		ProjectRisk projectRisk = new ProjectRisk();
		projectRisk.setProjectId(projectId);
		projectRisk.setStage(stage);
		projectRisk.setLevel(level);
		projectRisk.setMemo(riskMemo);
		projectRisk.setItcode(itCode);
		projectRisk.setResourceId(resId);
		projectRisk.setCreateTime(date);
		projectRisk.setPROCESS_INSTANCE_ID_(projectProcessInstanceId);
		boolean insertProjectRisk = this.riskService.insert(projectRisk);
		if (riskDesc != null && riskDesc.size() > 0 && riskStrategy != null && riskStrategy.size() > 0
				&& riskDesc.size() == riskStrategy.size()) {
			// 添加风险具体项
			Long projectRiskId = projectRisk.getEntityId();
			List<ProjectRiskItem> projectRiskItems = new ArrayList<>();
			int sizi = riskDesc.size();
			for (int i = 0; i < sizi; i++) {
				ProjectRiskItem projectRiskItem = new ProjectRiskItem();
				projectRiskItem.setProjectId(projectId);
				projectRiskItem.setRiskId(projectRiskId);
				projectRiskItem.setDesc(riskDesc.get(i));
				projectRiskItem.setStrategy(riskStrategy.get(i));
				projectRiskItems.add(projectRiskItem);
			}
			this.riskItemService.insertBatch(projectRiskItems);
		}
		return insertProjectApproval && insertProjectRisk;
	}

	@Override
	public List<ProjectApproval> getProjectExpertApproval(Long projectId, Long stage) {
		return this.baseMapper.getProjectExpertApproval(projectId, stage);
	}
}
