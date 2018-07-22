package com.river.ms.business.service.impl;

import com.river.core.business.EnumFlowType;
import com.river.core.entity.UserEntity;
import com.river.ms.business.entity.ActivitiResult;
import com.river.ms.business.entity.ProjectDocument;
import com.river.ms.business.entity.ProjectEvaluate;
import com.river.ms.business.entity.ProjectFlowStart;
import com.river.ms.business.feign.serviceImpl.ToActivitiService;
import com.river.ms.business.feign.serviceImpl.ToProjectImpl;
import com.river.ms.business.mapper.ProjectEvaluateDao;
import com.river.ms.business.service.MPProjectDocumentService;
import com.river.ms.business.service.MPProjectEvaluateService;
import com.river.ms.business.service.MPProjectFlowStartService;
import com.river.ms.business.service.MPProjectTodoService;
import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 项目实施评价记录 服务实现类
 * </p>
 *
 * @author zyb
 * @since 2017-12-21
 */
@Service
//@Transactional
public class ProjectEvaluateServiceDiy extends ServiceImpl<ProjectEvaluateDao, ProjectEvaluate> implements MPProjectEvaluateService {
	@Autowired
	ToProjectImpl toProject;

	@Autowired
	MPProjectTodoService projectTodoService;

	@Autowired
	ToActivitiService toActivitiService;
	
	@Autowired
	MPProjectFlowStartService projectFlowStartService;
	
	@Autowired
	MPProjectDocumentService projectDocumentService;
	
	@Override
	@Transactional
	public Boolean insertProjectEvaluate(ProjectEvaluate entity, Map<String, Object> headerMap,String docStr,UserEntity user) {
		Integer type = EnumFlowType.AFTER.getFlowType();
		Map<String, Object> projectCategoryId2ById = toProject.getProjectCategoryId2ById(headerMap,
				entity.getProjectId());
		String object = projectCategoryId2ById.get("groupCategoryId").toString();
		Long groupCategoryId = Long.valueOf(object);
		List<ProjectFlowStart> projectFlowStart = projectFlowStartService.getProjectFlowStart(null, groupCategoryId,
				type);
		String flowType = projectFlowStart.get(0).getFlow().getEntityCode();
		ActivitiResult startActiviti = toActivitiService.startActiviti(headerMap, entity.getItcode(),
				entity.getProjectId(), flowType, null);
		if (startActiviti.getStatus() == 0) {
			entity.setPROCESS_INSTANCE_ID_(startActiviti.getProcessInstanceId());
			entity.setCreateTime(new Date());
			boolean insert = this.insert(entity);
			this.toProject.setLiuchengshiliIdByProjectId(headerMap, entity.getProjectId(),
					startActiviti.getProcessInstanceId());
			if (insert) {
				String taskCode=startActiviti.getTasks().get(0).getTaskKey();
				// 根据项目阶段code获取项目阶段ID
				Map<Long, Long> projectStageIdByCode = toProject.getProjectStageIdByCode(headerMap,
						startActiviti.getTasks().get(0).getTaskKey());

				Map<Long, Long> projectStageNode = toProject.getProjectStageNode(headerMap,
						projectStageIdByCode.get(new Long(1)));
				Set<Entry<Long, Long>> entrySetProjectStageNode = projectStageNode.entrySet();
				Long bigStage = new Long(0);
				for (Entry<Long, Long> entry : entrySetProjectStageNode) {
					bigStage = entry.getKey();
				}
				// 设置项目阶段
				Boolean setProjectState = toProject.setProjectStage(headerMap, entity.getProjectId(),
						projectStageIdByCode.get(new Long(1)),startActiviti.getTaskMemo());
				Boolean setProjectState2 = toProject.setProjectState(headerMap, entity.getProjectId(), Integer.valueOf(startActiviti.getTaskState()));

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
				if(docStr != null && !docStr.equals("")) {
					List<ProjectDocument> entitys = JSONArray.parseArray(docStr, ProjectDocument.class);
					for (ProjectDocument e : entitys) {
						e.setItcode(user.getEntityCode());
						e.setResourceId(user.getResId());
						e.setResName(user.getEntityTitle());
						e.setCreateTime(new Date());
						e.setGreatStage(bigStage);
						e.setPROCESS_INSTANCE_ID_(startActiviti.getProcessInstanceId());
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
		return false;
	}

	@Override
	@Transactional
	public Boolean setProjectEvaluateState(Long entityId, Integer state, Map<String, Object> headerMap) {
		ProjectEvaluate entity = this.baseMapper.selectById(entityId);
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

}
