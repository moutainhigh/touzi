package com.river.ms.business.service.impl;

import com.river.core.business.EnumFlowType;
import com.river.core.entity.UserEntity;
import com.river.core.utils.ListToString;
import com.river.core.utils.SessionUtils;
import com.river.ms.business.entity.ActivitiResult;
import com.river.ms.business.entity.ProjectDocument;
import com.river.ms.business.entity.ProjectExpert;
import com.river.ms.business.entity.ProjectFeasibility;
import com.river.ms.business.entity.ProjectFlowStart;
import com.river.ms.business.feign.serviceImpl.ToActivitiService;
import com.river.ms.business.feign.serviceImpl.ToProjectImpl;
import com.river.ms.business.mapper.ProjectFeasibilityDao;
import com.river.ms.business.service.MPProjectDocumentService;
import com.river.ms.business.service.MPProjectExpertService;
import com.river.ms.business.service.MPProjectFeasibilityService;
import com.river.ms.business.service.MPProjectFlowStartService;
import com.river.ms.business.service.MPProjectTodoService;
import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 项目可研 服务实现类
 * </p>
 *
 * @author zyb
 * @since 2017-12-04
 */
@Service
//@Transactional
public class ProjectFeasibilityServiceDiy extends ServiceImpl<ProjectFeasibilityDao, ProjectFeasibility>
		implements MPProjectFeasibilityService {

	@Autowired
	ToProjectImpl toProject;

	@Autowired
	MPProjectTodoService projectTodoService;

	@Autowired
	ToActivitiService toActivitiService;

	@Autowired
	MPProjectFlowStartService projectFlowStartService;

	@Autowired
	MPProjectExpertService projectExpertService;

	@Autowired
	MPProjectDocumentService projectDocumentService;

	@Override
	@Transactional
	public Boolean insertProjectFeasibility(ProjectFeasibility entity, Map<String, Object> headerMap, String str,
			String docStr, UserEntity user) {

		boolean insert = this.insert(entity);
		if (insert) {
			Integer type = EnumFlowType.FEASIBILITY.getFlowType();
			Map<String, Object> projectCategoryId2ById = toProject.getProjectCategoryId2ById(headerMap,
					entity.getProjectId());
			String object = projectCategoryId2ById.get("groupCategoryId").toString();
			Long groupCategoryId = Long.valueOf(object);
			List<ProjectFlowStart> projectFlowStart = projectFlowStartService.getProjectFlowStart(null, groupCategoryId,
					type);
			if (projectFlowStart != null && projectFlowStart.size() > 0) {
				String flowType = projectFlowStart.get(0).getFlow().getEntityCode();

				ActivitiResult startActiviti = toActivitiService.startActiviti(headerMap, entity.getItcode(),
						entity.getProjectId(), flowType, null);
				if (startActiviti.getStatus() == 0) {
					String processInstanceId = startActiviti.getProcessInstanceId();
					entity.setPROCESS_INSTANCE_ID_(processInstanceId);
					this.updateById(entity);
					this.toProject.setLiuchengshiliIdByProjectId(headerMap, entity.getProjectId(), processInstanceId);

					// 根据项目阶段code获取项目阶段ID
					Map<Long, Long> projectStageIdByCode = toProject.getProjectStageIdByCode(headerMap,
							startActiviti.getTasks().get(0).getTaskKey());

					// 设置项目阶段
					Boolean setProjectState = toProject.setProjectStage(headerMap, entity.getProjectId(),
							projectStageIdByCode.get(new Long(1)), startActiviti.getTaskMemo());

					toProject.setProjectState(headerMap, entity.getProjectId(),
							Integer.valueOf(startActiviti.getTaskState()));

					Map<Long, Long> projectStageNode = toProject.getProjectStageNode(headerMap,
							projectStageIdByCode.get(new Long(1)));
					Set<Entry<Long, Long>> entrySetProjectStageNode = projectStageNode.entrySet();
					Long bigStage = new Long(0);
					for (Entry<Long, Long> entry : entrySetProjectStageNode) {
						bigStage = entry.getKey();
					}

					// 添加操作用户
					String taskCode = startActiviti.getTasks().get(0).getTaskKey();
					this.projectTodoService.deleteTodo(entity.getProjectId(), null, null, null, null);
					this.projectTodoService.insertTodos(entity.getProjectId(), taskCode);
					/*
					 * Map<Long, List<Long>> roleAndUserByProjectIdAndStage = this.toProject
					 * .getRoleAndUserByProjectIdAndStage(headerMap, entity.getProjectId());
					 * Set<Entry<Long, List<Long>>> entrySet =
					 * roleAndUserByProjectIdAndStage.entrySet(); for (Entry<Long, List<Long>> e :
					 * entrySet) { if (e.getValue() != null && e.getValue().size() > 0) { for (Long
					 * l : e.getValue()) { this.projectTodoService.insertTodo(entity.getProjectId(),
					 * e.getKey(), l, null, 0); } } else {
					 * this.projectTodoService.insertTodo(entity.getProjectId(), e.getKey(), null,
					 * null, 0); } }
					 */
					if (str != null && !str.equals("")) {
						List<ProjectExpert> entitys = JSONArray.parseArray(str, ProjectExpert.class);
						for (ProjectExpert p : entitys) {
							p.setState(0);
							p.setCreateTime(new Date());
							p.setStage(bigStage);
							p.setPROCESS_INSTANCE_ID_(processInstanceId);
						}
						boolean insertBatch = this.projectExpertService.insertProjectExpert(entitys);
					}
					if (docStr != null && !docStr.equals("")) {
						List<ProjectDocument> entitys = JSONArray.parseArray(docStr, ProjectDocument.class);
						for (ProjectDocument e : entitys) {
							e.setItcode(user.getEntityCode());
							e.setResourceId(user.getResId());
							e.setResName(user.getEntityTitle());
							e.setCreateTime(new Date());
							e.setGreatStage(bigStage);
							e.setPROCESS_INSTANCE_ID_(processInstanceId);
						}
						this.projectDocumentService.insertBatch(entitys);
					}
					if (setProjectState) {
						return true;
					} else {
						return false;
					}
				}
			}
		}
		return false;
	}

	@Override
	@Transactional
	public Boolean setProjectFeasibilityState(Long entityId, Integer state, Map<String, Object> headerMap) {
		ProjectFeasibility entity = this.baseMapper.selectById(entityId);
		if (entity != null) {
			entity.setState(state);
			Integer updateById = this.baseMapper.updateById(entity);
			if (updateById != null) {
				Boolean setProjectState = toProject.setProjectState(headerMap, entity.getProjectId(),
						entity.getState());
				if (setProjectState) {
					return true;
				} else {
					return false;
				}
			} else {
				return false;
			}
		}
		return false;
	}

	@Override
	@Transactional
	public Boolean updateProjectFeasibilityState(HttpServletRequest request, ProjectFeasibility entity, String docStr) {
		Long projectId = entity.getProjectId();
		this.updateById(entity);
		UserEntity user = SessionUtils.getUser(request);
		// 完成操作
		// 获取项目节点code
		Map<String, Object> headerMap = SessionUtils.getHeaderMap(request);
		String stageCode = this.toProject.getStageByProjectId(headerMap, projectId);
		String liuchengshiliIdByProjectId = this.toProject.getLiuchengshiliIdByProjectId(headerMap, projectId);
		
		if(docStr != null && !docStr.equals("")) {
			List<ProjectDocument> entitys = JSONArray.parseArray(docStr, ProjectDocument.class);
			
			Long stageId = toProject.getStageIdByProjectId(headerMap, projectId);
			Long greatStageId = new Long(0);
			if (stageId != null && stageId != 0) {
				Map<Long, Long> greatStage = toProject.getProjectStageNode(headerMap, stageId);
				Set<Entry<Long, Long>> entrySet = greatStage.entrySet();
				for (Entry<Long, Long> entry : entrySet) {
					greatStageId = entry.getKey();
				}
			}
			
			for (ProjectDocument e : entitys) {
				e.setItcode(user.getEntityCode());
				e.setResourceId(user.getResId());
				e.setResName(user.getEntityTitle());
				e.setCreateTime(new Date());
				e.setGreatStage(greatStageId);
				e.setStage(stageId);
				e.setPROCESS_INSTANCE_ID_(liuchengshiliIdByProjectId);
			}
			this.projectDocumentService.insertBatch(entitys);
		}
		
		// 完成操作
		Map<String, Object> activitiQueryMap = new HashMap<>();
		List<String> keys = new ArrayList<>();
		keys.add("reApply");
		keys.add("isPass");
		keys.add("userId");
		keys.add("projectId");
		keys.add("reason");
		keys.add("taskKey");
		keys.add("processInstanceId");
		List<Object> values = new ArrayList<>();
		values.add(true);
		values.add(2);
		values.add(user.getEntityCode());
		values.add(projectId);
		values.add("");
		values.add(stageCode);
		values.add(liuchengshiliIdByProjectId);
		List<String> types = new ArrayList<>();
		types.add("B");
		types.add("I");
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
			if (nodeEnd.getTasks() == null) {
				// String projectNextStage = toProject.getProjectNextStage(headerMap,
				// stageCode);
				// 根据项目阶段code获取项目阶段ID
				toProject.setProjectStage(headerMap, projectId, 0l, nodeEnd.getTaskMemo());
				toProject.setProjectState(headerMap, projectId, Integer.valueOf(nodeEnd.getTaskState()));
				toProject.setLiuchengshiliIdByProjectId(headerMap, projectId, null);
				this.projectTodoService.deleteTodo(projectId, null, null, null, 0);
				return true;
			} else {
				// 根据项目阶段code获取项目阶段ID
				Map<Long, Long> projectStageIdByCode = toProject.getProjectStageIdByCode(headerMap,
						nodeEnd.getTasks().get(0).getTaskKey());
				// 设置项目阶段
				toProject.setProjectStage(headerMap, projectId, projectStageIdByCode.get(new Long(1)),
						nodeEnd.getTaskMemo());
				toProject.setProjectState(headerMap, projectId, Integer.valueOf(nodeEnd.getTaskState()));
				// 添加操作用户
				String taskCode = nodeEnd.getTasks().get(0).getTaskKey();
				this.projectTodoService.deleteTodo(projectId, null, null, null, 0);
				this.projectTodoService.insertTodos(projectId, taskCode);
				/*
				 * Map<Long, List<Long>> roleAndUserByProjectIdAndStage = this.toProject
				 * .getRoleAndUserByProjectIdAndStage(headerMap, projectId); Set<Entry<Long,
				 * List<Long>>> entrySet = roleAndUserByProjectIdAndStage.entrySet(); for
				 * (Entry<Long, List<Long>> e : entrySet) { if (e.getValue() != null &&
				 * e.getValue().size() > 0) { for (Long l : e.getValue()) {
				 * this.projectTodoService.insertTodo(projectId, e.getKey(), l, null, 0); } }
				 * else { this.projectTodoService.insertTodo(projectId, e.getKey(), null, null,
				 * 0); } }
				 */
				return true;
			}
		} else {
			return false;
		}
	}

}
