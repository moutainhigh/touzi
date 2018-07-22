package com.river.ms.project.service.impl;

import com.river.core.business.EnumRole;
import com.river.core.entity.EmployeeEntity;
import com.river.core.entity.RoleEntity;
import com.river.core.entity.StationEmployeeEntity;
import com.river.core.entity.UserEntity;
import com.river.core.result.JsonResult;
import com.river.ms.project.entity.ProjectList;
import com.river.ms.project.entity.ProjectNode;
import com.river.ms.project.entity.ProjectStage;
import com.river.ms.project.mapper.ProjectNodeDao;
import com.river.ms.project.service.MPProjectListService;
import com.river.ms.project.service.MPProjectNodeRoleService;
import com.river.ms.project.service.MPProjectNodeService;
import com.river.ms.project.service.MPProjectStageService;
import com.river.ms.project.service.feign.impl.ToBusinessImpl;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 流程节点定义 服务实现类
 * </p>
 *
 * @author zyb
 * @since 2017-12-08
 */
@Service
public class ProjectNodeServiceDiy extends ServiceImpl<ProjectNodeDao, ProjectNode> implements MPProjectNodeService {

	@Autowired
	MPProjectListService projectListService;

	@Autowired
	MPProjectStageService projectStageService;

	@Autowired
	MPProjectNodeRoleService projectNodeRoleService;

	@Autowired
	ToBusinessImpl toBusines;

	/*
	 * 根据角色id查找node节点
	 */
	@Override
	public List<ProjectNode> getProjectNodeByRoles(Map<String, Object> headerMap, List<RoleEntity> roles,
			UserEntity user) {
		/*
		 * for (RoleEntity r : roles) { List<ProjectNode> projectNodeByRole =
		 * this.baseMapper.getProjectNodeByRole(r.getEntityId()); for (ProjectNode p :
		 * projectNodeByRole) { List<ProjectStage> projectStage =
		 * projectStageService.getProjectStageByNode(p.getEntityId()); Map<String,
		 * Object> queryMap = new HashMap<>(); List<Long> stageCode = new ArrayList<>();
		 * for(ProjectStage stage : projectStage) { stageCode.add(stage.getEntityId());
		 * } queryMap.put("roleCode", r.getEntityCode()); queryMap.put("stageCode",
		 * stageCode); Map<Long, Long> projectList =
		 * this.toBusines.getProjectList(headerMap, queryMap); List<ProjectList>
		 * projectListByIds = this.projectListService.getProjectListByIds(projectList);
		 * p.setProjectList(projectListByIds); result.add(p); } }
		 */
		List<ProjectNode> result = new ArrayList<>();
		HashMap<String,Object> map=new HashMap<String,Object>();
		map.put("keyword", "");
		map.put("itcode",user.getEntityCode());
		map.put("bPage", 0);
		List<ProjectList> projectTodoList = projectListService.getProjectTodoListPC(map);
		for (RoleEntity r : roles) {
			// JsonResult projectToDo = toBusines.getProjectToDo(headerMap,
			// r.getEntityId());
			List<ProjectNode> projectNodeByRole = this.baseMapper.getProjectNodeByRole(r.getEntityId());
			for (ProjectList p : projectTodoList) {
				ProjectStage selectById = this.projectStageService.selectById(p.getStageCode());
				if (selectById != null) {
					for (ProjectNode node : projectNodeByRole) {
						if (node.getEntityId().equals(selectById.getSrc())) {
							node.getProjectList().add(p);
						}
					}
				}
			}
			/*
			 * if (projectToDo.getData() != null) { List<Long> projectIds = (List<Long>)
			 * projectToDo.getData(); if (projectIds != null && projectIds.size() > 0) {
			 * Map<String, Object> columnMap = new HashMap<>(); EntityWrapper<ProjectList>
			 * wrapper = new EntityWrapper<>(); wrapper.in("entityId", projectIds); if
			 * (r.getEntityCode().equals(EnumRole.Thinktank.getRole()) ||
			 * r.getEntityCode().equals(EnumRole.Groupleader.getRole())) { EmployeeEntity
			 * employee = user.getEmployee(); List<StationEmployeeEntity> stationEmployees =
			 * employee.getStationEmployees(); String entityCode = new String();
			 * for(StationEmployeeEntity s : stationEmployees) { if (s.getIsChief() == 1) {
			 * entityCode = s.getOrganization().getEntityCode(); } }
			 * wrapper.eq("owner_group", entityCode); } List<ProjectList> selectList =
			 * this.projectListService.selectList(wrapper); for (ProjectList p : selectList)
			 * { ProjectStage selectById =
			 * this.projectStageService.selectById(p.getStageCode()); if (selectById !=
			 * null) { for (ProjectNode node : projectNodeByRole) { if
			 * (node.getEntityId().equals(selectById.getSrc())) {
			 * node.getProjectList().add(p); } } } } } }
			 */
			result.addAll(projectNodeByRole);
		}
		return result;
	}

	@Override
	public Long exist(Long id, String entityCode) {
		return this.baseMapper.exist(id,entityCode);
	}

}
