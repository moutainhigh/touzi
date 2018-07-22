package com.river.ms.business.service.impl;

import com.river.core.utils.ListToString;
import com.river.ms.business.entity.ActivitiResult;
import com.river.ms.business.entity.ProjectEvaluateRectify;
import com.river.ms.business.feign.serviceImpl.ToActivitiService;
import com.river.ms.business.feign.serviceImpl.ToProjectImpl;
import com.river.ms.business.mapper.ProjectEvaluateRectifyDao;
import com.river.ms.business.service.MPProjectEvaluateRectifyService;
import com.river.ms.business.service.MPProjectTodoService;
import com.river.ms.business.utils.ResultCode;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 项目反馈/整改审核 服务实现类
 * </p>
 *
 * @author zyb
 * @since 2017-12-22
 */
@Service
//@Transactional
public class ProjectEvaluateRectifyServiceDiy extends ServiceImpl<ProjectEvaluateRectifyDao, ProjectEvaluateRectify>
		implements MPProjectEvaluateRectifyService {

	@Autowired
	ToProjectImpl toProject;

	@Autowired
	MPProjectTodoService projectTodoService;

	@Autowired
	ToActivitiService toActivitiService;
	
	@Override
	@Transactional
	public boolean insertProjectEvaluateRectify(ProjectEvaluateRectify entity, Map<String, Object> headerMap) {
		boolean insert = this.insert(entity);
		if (insert) {
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
			keys.add("isPass");
			values.add(entity.getFeedbackAuditor());
			values.add(entity.getProjectId());
			values.add(stageCode);
			values.add(liuchengshiliIdByProjectId);
			values.add(ResultCode.getReslt(entity.getState()));
			types.add("S");
			types.add("S");
			types.add("S");
			types.add("S");
			types.add("I");
			activitiQueryMap.put("keys", ListToString.toString(keys));
			activitiQueryMap.put("values", ListToString.toString(values));
			activitiQueryMap.put("types", ListToString.toString(types));
			ActivitiResult nodeEnd = toActivitiService.nodeEnd(headerMap, activitiQueryMap);
			if (nodeEnd.getStatus() == 0) {
				if (nodeEnd.getTasks() == null) {
					// 设置项目阶段
					toProject.setProjectStage(headerMap, entity.getProjectId(), 0l,nodeEnd.getTaskMemo());
					toProject.setProjectState(headerMap, entity.getProjectId(), Integer.valueOf(nodeEnd.getTaskState()));
					toProject.setLiuchengshiliIdByProjectId(headerMap, entity.getProjectId(), null);
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

}
