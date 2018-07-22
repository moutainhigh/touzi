package com.river.ms.business.service.impl;

import com.river.core.entity.UserEntity;
import com.river.core.utils.ListToString;
import com.river.core.utils.SessionUtils;
import com.river.ms.business.entity.ActivitiResult;
import com.river.ms.business.entity.ProjectDocument;
import com.river.ms.business.entity.ProjectEvaluateFeedback;
import com.river.ms.business.feign.serviceImpl.ToActivitiService;
import com.river.ms.business.feign.serviceImpl.ToProjectImpl;
import com.river.ms.business.mapper.ProjectEvaluateFeedbackDao;
import com.river.ms.business.service.MPProjectDocumentService;
import com.river.ms.business.service.MPProjectEvaluateFeedbackService;
import com.river.ms.business.service.MPProjectTodoService;
import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

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
 * 项目评价反馈 服务实现类
 * </p>
 *
 * @author zyb
 * @since 2017-12-22
 */
@Service
//@Transactional
public class ProjectEvaluateFeedbackServiceDiy extends ServiceImpl<ProjectEvaluateFeedbackDao, ProjectEvaluateFeedback>
		implements MPProjectEvaluateFeedbackService {

	@Autowired
	ToProjectImpl toProject;

	@Autowired
	MPProjectTodoService projectTodoService;

	@Autowired
	ToActivitiService toActivitiService;
	
	@Autowired
	MPProjectDocumentService projectDocumentService;

	@Override
	@Transactional
	public boolean insertEvaluateFeedback(ProjectEvaluateFeedback entity, Map<String, Object> headerMap,HttpServletRequest request, String docStr) {
		boolean insert = this.insert(entity);
		if(insert) {
			if (docStr != null && !docStr.equals("")) {
				this.insertProjectDocument(docStr, request);
			}
			Map<String, Object> activitiQueryMap = new HashMap<>();
			String stageCode = this.toProject.getStageByProjectId(headerMap, entity.getProjectId());
			String liuchengshiliIdByProjectId = this.toProject.getLiuchengshiliIdByProjectId(headerMap,
					entity.getProjectId());
			List<String> keys = new ArrayList<>();
			List<Object> values = new ArrayList<>();
			List<String> types = new ArrayList<>();
			keys.add("userId");
			keys.add("projectId");
			keys.add("taskKey");
			keys.add("processInstanceId");
			values.add(entity.getFeedbackItcode());
			values.add(entity.getProjectId());
			values.add(stageCode);
			values.add(liuchengshiliIdByProjectId);
			types.add("S");
			types.add("S");
			types.add("S");
			types.add("S");
			activitiQueryMap.put("keys", ListToString.toString(keys));
			activitiQueryMap.put("values", ListToString.toString(values));
			activitiQueryMap.put("types", ListToString.toString(types));
			ActivitiResult nodeEnd = toActivitiService.nodeEnd(headerMap, activitiQueryMap);
			if (nodeEnd.getStatus() == 0) {
				if (nodeEnd.getTasks() == null) {
					// 设置项目阶段
					toProject.setProjectStage(headerMap, entity.getProjectId(), 0l,nodeEnd.getTaskMemo());
					toProject.setLiuchengshiliIdByProjectId(headerMap, entity.getProjectId(), null);
					toProject.setProjectState(headerMap, entity.getProjectId(), Integer.valueOf(nodeEnd.getTaskState()));
					// 添加操作用户
					this.projectTodoService.deleteTodo(entity.getProjectId(), null, null, null, null);
					return true;
				} else {
					String taskCode=nodeEnd.getTasks().get(0).getTaskKey();
					// 根据项目阶段code获取项目阶段ID
					Map<Long, Long> projectStageIdByCode = toProject.getProjectStageIdByCode(headerMap,
							nodeEnd.getTasks().get(0).getTaskKey());
					// 设置项目阶段
					Boolean setProjectState = toProject.setProjectStage(headerMap, entity.getProjectId(),
							projectStageIdByCode.get(new Long(1)),nodeEnd.getTaskMemo());
					toProject.setProjectState(headerMap, entity.getProjectId(), Integer.valueOf(nodeEnd.getTaskState()));
					if (setProjectState) {
						// 添加操作用户
						this.projectTodoService.deleteTodo(entity.getProjectId(), null, null, null, 0);
						this.projectTodoService.insertTodos(entity.getProjectId(), taskCode);
						/*Map<Long, List<Long>> roleAndUserByProjectIdAndStage = this.toProject
								.getRoleAndUserByProjectIdAndStage(headerMap, entity.getProjectId());
						Set<Entry<Long, List<Long>>> entrySet = roleAndUserByProjectIdAndStage.entrySet();
						for (Entry<Long, List<Long>> e : entrySet) {
							if (e.getValue() != null && e.getValue().size() > 0) {
								for (Long l : e.getValue()) {
									this.projectTodoService.insertTodo(entity.getProjectId(), e.getKey(), l, null, 0);
								}
							} else {
								this.projectTodoService.insertTodo(entity.getProjectId(), e.getKey(), null, null, 0);
							}
						}*/
						return true;
					} else {
						return false;
					}
				}
			} else {
				return false;
			}
		}
		return false;
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

}
