package com.river.ms.business.service.impl;

import com.river.core.entity.UserEntity;
import com.river.core.utils.ListToString;
import com.river.core.utils.SessionUtils;
import com.river.ms.business.entity.ActivitiResult;
import com.river.ms.business.entity.ProjectCouncil;
import com.river.ms.business.entity.ProjectCouncilMember;
import com.river.ms.business.entity.ProjectDecision;
import com.river.ms.business.entity.ProjectModification;
import com.river.ms.business.feign.serviceImpl.ToActivitiService;
import com.river.ms.business.feign.serviceImpl.ToProjectImpl;
import com.river.ms.business.mapper.ProjectCouncilDao;
import com.river.ms.business.service.MPProjectCouncilService;
import com.river.ms.business.service.MPProjectDecisionService;
import com.river.ms.business.service.MPProjectModificationService;
import com.river.ms.business.service.MPProjectTodoService;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 项目投决评议会，变更评审会 服务实现类
 * </p>
 *
 * @author zyb
 * @since 2017-12-04
 */
@Service
//@Transactional
public class ProjectCouncilServiceDiy extends ServiceImpl<ProjectCouncilDao, ProjectCouncil>
		implements MPProjectCouncilService {

	/**
	 * 日志
	 */
	private final Logger logger = Logger.getLogger(getClass());

	@Autowired
	private ProjectCouncilMemberServiceDiy projectCouncilMemberServiceDiy;

	@Autowired
	ToProjectImpl toProject;

	@Autowired
	ToActivitiService toActivitiService;

	@Autowired
	MPProjectTodoService projectTodoService;
	
	@Autowired
	MPProjectDecisionService decisionService;
	
	@Autowired
	MPProjectModificationService modificationService;

	/**
	 * 保存项目投决评议会，变更评审会，以及参会人员
	 * 
	 * @throws Exception
	 */
	@Override
	@Transactional
	public void insertProjectCouncilAndMember(ProjectCouncil entity, HttpServletRequest request) throws Exception {
		try {
			// 保存项目投决评议会，变更评审会
			boolean insert = this.insert(entity);
			List<ProjectCouncilMember> memberList = entity.getProjectCouncilMemberList();
			if (memberList != null && !memberList.isEmpty()) {
				for (ProjectCouncilMember m : memberList) {
					m.setCouncilId(entity.getEntityId());
					m.setCreateTime(new Date());
				}
				boolean insertBatch = this.projectCouncilMemberServiceDiy.insertBatch(memberList);
			}
			//添加会议成员
			if (insert) {
				UserEntity user = SessionUtils.getUser(request);
				// 完成操作
				// 获取项目节点code
				Map<String, Object> headerMap = SessionUtils.getHeaderMap(request);
				String stageCode = this.toProject.getStageByProjectId(headerMap, entity.getProjectId());
				String liuchengshiliIdByProjectId = this.toProject.getLiuchengshiliIdByProjectId(headerMap,
						entity.getProjectId());
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
				values.add(entity.getProjectId());
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
					Boolean setProjectState = toProject.setProjectStage(headerMap, entity.getProjectId(),
							projectStageIdByCode.get(new Long(1)),nodeEnd.getTaskMemo());
					toProject.setProjectState(headerMap, entity.getProjectId(), Integer.valueOf(nodeEnd.getTaskState()));
					if (setProjectState) {
						// 添加操作用户
						String taskCode=nodeEnd.getTasks().get(0).getTaskKey();
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
					}
				}
				//改变会议信息（线上，线下）
				if(entity.getEntityType() == 1) {
					EntityWrapper<ProjectDecision> decisionWrapper = new EntityWrapper<>();
					decisionWrapper.eq("projectId", entity.getProjectId());
					ProjectDecision decision = this.decisionService.selectOne(decisionWrapper);
					decision.setOnline(entity.getOnline());
					this.decisionService.updateById(decision);
				}
				if(entity.getEntityType() == 2) {
					EntityWrapper<ProjectModification> modificationWrapper = new EntityWrapper<>();
					modificationWrapper.eq("projectId", entity.getProjectId());
					ProjectModification modification = this.modificationService.selectOne(modificationWrapper);
					modification.setOnline(entity.getOnline());
					this.modificationService.updateById(modification);
				}
			}
		} catch (Exception e) {
			logger.debug(" insertProjectCouncil error!");
			throw new Exception(e);
		}

	}

}
