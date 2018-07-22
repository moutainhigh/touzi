package com.river.ms.business.service.impl;

import com.river.core.business.EnumFlowType;
import com.river.core.entity.UserEntity;
import com.river.core.utils.ListToString;
import com.river.core.utils.SessionUtils;
import com.river.ms.business.entity.ActivitiResult;
import com.river.ms.business.entity.ProjectDocument;
import com.river.ms.business.entity.ProjectExpert;
import com.river.ms.business.entity.ProjectFlowStart;
import com.river.ms.business.entity.ProjectFlowVariable;
import com.river.ms.business.entity.ProjectSetup;
import com.river.ms.business.feign.serviceImpl.ToActivitiService;
import com.river.ms.business.feign.serviceImpl.ToProjectImpl;
import com.river.ms.business.mapper.ProjectSetupDao;
import com.river.ms.business.service.MPProjectDocumentService;
import com.river.ms.business.service.MPProjectExpertService;
import com.river.ms.business.service.MPProjectFlowStartService;
import com.river.ms.business.service.MPProjectFlowVariableService;
import com.river.ms.business.service.MPProjectSetupService;
import com.river.ms.business.service.MPProjectTodoService;
import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 项目立项申请 服务实现类
 * </p>
 *
 * @author zyb
 * @since 2017-12-04
 */
@Service
public class ProjectSetupServiceDiy extends ServiceImpl<ProjectSetupDao, ProjectSetup>
		implements MPProjectSetupService {

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
	
	@Autowired
	MPProjectFlowVariableService projectFlowVariableService;

	@Override
	public Boolean insertProjectSetup(ProjectSetup project, Map<String, Object> headerMap, String str,String docStr,UserEntity user) {
		// 保存项目立项信息
		project.setState(0);
		project.setCreateTime(new Date());
		boolean insert = this.insert(project);
		if (insert) {
			// 获取流程类型
			Integer type = EnumFlowType.SETUP.getFlowType();
			// 获取项目类型
			Map<String, Object> projectCategoryId2ById = toProject.getProjectCategoryId2ById(headerMap,
					project.getProjectId());
			String object = projectCategoryId2ById.get("groupCategoryId").toString();
			Long groupCategoryId = Long.valueOf(object);
			// 获取流程类型实例
			List<ProjectFlowStart> projectFlowStart = projectFlowStartService.getProjectFlowStart(null, groupCategoryId,
					type);
			if (projectFlowStart != null && projectFlowStart.size() > 0) {
				String flowType = projectFlowStart.get(0).getFlow().getEntityCode();
				//执行流程
				ActivitiResult startActiviti = toActivitiService.startActiviti(headerMap, project.getItcode(),
						project.getProjectId(), flowType, null);
				if (startActiviti.getStatus() == 0) {
					// 设置立项流程实例ID
					String processInstanceId = startActiviti.getProcessInstanceId();
					project.setPROCESS_INSTANCE_ID_(processInstanceId);
					boolean updateById = this.updateById(project);
					if (updateById) {
						// 设置项目的流程实例ID
						this.toProject.setLiuchengshiliIdByProjectId(headerMap, project.getProjectId(),
								processInstanceId);
						// 根据项目阶段code获取项目阶段ID
						Map<Long, Long> projectStageIdByCode = toProject.getProjectStageIdByCode(headerMap,
								startActiviti.getTasks().get(0).getTaskKey());
						// 设置项目阶段
						Boolean setProjectState = toProject.setProjectStage(headerMap, project.getProjectId(),
								projectStageIdByCode.get(new Long(1)), startActiviti.getTaskMemo());
						// 设置项目状态
						Boolean setProjectState2 = toProject.setProjectState(headerMap, project.getProjectId(),
								Integer.valueOf(startActiviti.getTaskState()));
						// 获取项目阶段（大阶段，设置专家阶段）
						Map<Long, Long> projectStageNode = toProject.getProjectStageNode(headerMap,
								projectStageIdByCode.get(new Long(1)));
						Set<Entry<Long, Long>> entrySetProjectStageNode = projectStageNode.entrySet();
						Long bigStage = new Long(0);
						for (Entry<Long, Long> entry : entrySetProjectStageNode) {
							bigStage = entry.getKey();
						}
						if (setProjectState) {
							String taskCode = startActiviti.getTasks().get(0).getTaskKey();
							// 添加操作用户
							this.projectTodoService.deleteTodo(project.getProjectId(), null, null, null, null);
							Map<String, Object> insertTodos = this.projectTodoService
									.insertTodos(project.getProjectId(), taskCode);
							/*
							 * Map<Long, List<Long>> roleAndUserByProjectIdAndStage = this.toProject
							 * .getRoleAndUserByProjectIdAndStage(headerMap, project.getProjectId());
							 * Set<Entry<Long, List<Long>>> entrySet =
							 * roleAndUserByProjectIdAndStage.entrySet(); for (Entry<Long, List<Long>> e :
							 * entrySet) { if (e.getValue() != null && e.getValue().size() > 0) { for (Long
							 * l : e.getValue()) {
							 * this.projectTodoService.insertTodo(project.getProjectId(), e.getKey(), l,
							 * null, 0); } } else {
							 * this.projectTodoService.insertTodo(project.getProjectId(), e.getKey(), null,
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
							if(docStr != null && !docStr.equals("")) {
								List<ProjectDocument> entitys = JSONArray.parseArray(docStr, ProjectDocument.class);
								for (ProjectDocument entity : entitys) {
									entity.setItcode(user.getEntityCode());
									entity.setResourceId(user.getResId());
									entity.setResName(user.getEntityTitle());
									entity.setCreateTime(new Date());
									entity.setGreatStage(bigStage);
									entity.setPROCESS_INSTANCE_ID_(processInstanceId);
								}
								this.projectDocumentService.insertBatch(entitys);
							}
							return true;
						}
					}
				}
			}
		}
		return false;
	}

	@Override
	//@Transactional
	public Boolean setProjectSetupState(Long projectId, Integer state, Map<String, Object> headerMap) {
		ProjectSetup selectById = this.selectById(projectId);
		if (selectById != null) {
			selectById.setState(state);
			boolean updateById = this.updateById(selectById);
			if (updateById) {
				Boolean setProjectState = toProject.setProjectState(headerMap, selectById.getProjectId(),
						selectById.getState());
				if (setProjectState) {
					return true;
				} else {
					return false;
				}
			}
			return false;
		}
		return false;
	}

	@Override
	//@Transactional
	public Boolean updateProjectSetup(HttpServletRequest request,ProjectSetup entity,String docStr) {
		
		this.updateById(entity);
		Long projectId = entity.getProjectId();
		UserEntity user = SessionUtils.getUser(request);
		// 完成操作
		// 获取项目节点code
		Map<String, Object> headerMap = SessionUtils.getHeaderMap(request);
		String stageCode = this.toProject.getStageByProjectId(headerMap, projectId);
		String liuchengshiliIdByProjectId = this.toProject.getLiuchengshiliIdByProjectId(headerMap, projectId);
		System.out.println("流程实例ID:"+liuchengshiliIdByProjectId);
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
		System.out.println(ListToString.toString(keys));
		System.out.println(ListToString.toString(values));
		System.out.println(ListToString.toString(types));
		activitiQueryMap.put("keys", ListToString.toString(keys));
		activitiQueryMap.put("values", ListToString.toString(values));
		activitiQueryMap.put("types", ListToString.toString(types));
		ActivitiResult nodeEnd = this.toActivitiService.nodeEnd(headerMap, activitiQueryMap);
		if (nodeEnd.getStatus() == 0) {
			if (nodeEnd.getTasks() == null) {
				//String projectNextStage = toProject.getProjectNextStage(headerMap, stageCode);
				// 根据项目阶段code获取项目阶段ID
				toProject.setProjectStage(headerMap, projectId, 0l, nodeEnd.getTaskMemo());
				toProject.setProjectState(headerMap, projectId, Integer.valueOf(nodeEnd.getTaskState()));
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
