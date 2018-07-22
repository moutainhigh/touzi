package com.river.ms.business.service.impl;

import com.river.core.entity.UserEntity;
import com.river.core.utils.ListToString;
import com.river.core.utils.SessionUtils;
import com.river.ms.business.entity.ActivitiResult;
import com.river.ms.business.entity.ProjectCondition;
import com.river.ms.business.feign.serviceImpl.ToActivitiService;
import com.river.ms.business.feign.serviceImpl.ToProjectImpl;
import com.river.ms.business.mapper.ProjectConditionDao;
import com.river.ms.business.service.MPProjectConditionService;
import com.river.ms.business.service.MPProjectOpinionConditionService;
import com.river.ms.business.service.MPProjectTodoService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 项目前置条件（赋能群确认) 服务实现类
 * </p>
 *
 * @author zyb
 * @since 2017-12-04
 */
@Service
//@Transactional
public class ProjectConditionServiceDiy extends ServiceImpl<ProjectConditionDao, ProjectCondition>
		implements MPProjectConditionService {

	@Autowired
	MPProjectOpinionConditionService service;

	@Autowired
	ToProjectImpl toProject;

	@Autowired
	MPProjectTodoService projectTodoService;

	@Autowired
	ToActivitiService toActivitiService;

	@Override
	@Transactional
	public Boolean insertProjectConditions(HttpServletRequest request, Long projectId) {
		UserEntity user = SessionUtils.getUser(request);
		// 完成操作
		// 获取项目节点code
		Map<String, Object> headerMap = SessionUtils.getHeaderMap(request);
		String stageCode = this.toProject.getStageByProjectId(headerMap, projectId);
		String liuchengshiliIdByProjectId = this.toProject.getLiuchengshiliIdByProjectId(headerMap, projectId);
		// 完成操作
		Map<String, Object> activitiQueryMap = new HashMap<>();
		List<String> keys = new ArrayList<>();
		keys.add("isPass");
		keys.add("userId");
		keys.add("projectId");
		keys.add("reason");
		keys.add("taskKey");
		keys.add("processInstanceId");
		List<Object> values = new ArrayList<>();
		values.add(2);
		values.add(user.getEntityCode());
		values.add(projectId);
		values.add("");
		values.add(stageCode);
		values.add(liuchengshiliIdByProjectId);
		List<String> types = new ArrayList<>();
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
				this.projectTodoService.deleteTodo(projectId, null, null, null, null);
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
				String taskCode=nodeEnd.getTasks().get(0).getTaskKey();
				this.projectTodoService.deleteTodo(projectId, null, null, null, 0);
				this.projectTodoService.insertTodos(projectId, taskCode);
				/*Map<Long, List<Long>> roleAndUserByProjectIdAndStage = this.toProject
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
				}*/
				return true;
			}
		} else {
			return false;
		}
	}

}
