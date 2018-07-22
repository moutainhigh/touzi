package com.river.ms.business.web;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.river.core.entity.UserEntity;
import com.river.core.result.JsonResult;
import com.river.core.utils.SessionUtils;
import com.river.ms.business.entity.ProjectCouncil;
import com.river.ms.business.entity.ProjectCouncilMember;
import com.river.ms.business.feign.serviceImpl.ToProjectImpl;
import com.river.ms.business.result.ErrorResult;
import com.river.ms.business.service.MPProjectCouncilMemberService;
import com.river.ms.business.service.MPProjectCouncilService;

/**
 * <p>
 * 项目投决评议会，变更评审会 前端控制器
 * </p>
 *
 * @author zyb
 * @since 2017-12-04
 */
@RestController
@RequestMapping("/projectCouncil")
public class ProjectCouncilAction {

	@Autowired
	MPProjectCouncilService service;

	@Autowired
	MPProjectCouncilMemberService projectCouncilMemberService;
	
	@Autowired
	ToProjectImpl toProject;

	/**
	 * 添加项目投决评议会，变更评审会申请
	 * 
	 * @param request
	 * @param entity
	 * @return
	 */
	@RequestMapping(value = "/insertProjectCouncil", method = RequestMethod.POST)
	public JsonResult insertProjectCouncil(HttpServletRequest request,@RequestParam(name = "str") String str) {
		
		ProjectCouncil entity = JSONObject.parseObject(str, ProjectCouncil.class);
		
		Map<String, Object> headerMap = SessionUtils.getHeaderMap(request);
		Boolean verifyStage = toProject.verifyStage(headerMap, entity.getProjectId(), entity.getStage());
		if (!verifyStage) {
			return ErrorResult.PROJECT_STAGE_ERROR;
		}
		
		String liuchengshiliIdByProjectId = toProject.getLiuchengshiliIdByProjectId(headerMap, entity.getProjectId());
		
		UserEntity user = SessionUtils.getUser(request);
		
		Long projectId = entity.getProjectId();
		Integer projectState = toProject.getProjectState(headerMap, projectId);
		if (projectState >= 15 && projectState < 20) {
			entity.setEntityType(1);
		}
		if (projectState >= 20 && projectState < 40) {
			entity.setEntityType(2);
		}
		entity.setApplicationId(user.getResId());
		entity.setCreateTime(new Date());
		entity.setItcode(user.getEntityCode());
		entity.setResourceId(user.getResId());
		entity.setPROCESS_INSTANCE_ID_(liuchengshiliIdByProjectId);
		try {
			this.service.insertProjectCouncilAndMember(entity, request);
		} catch (Exception e) {
			return ErrorResult.INSERT_PROJECT_ERROE;
		}
		return JsonResult.success(entity);
	}

	/**
	 * 查询项目投决评议会，变更评审会
	 * 
	 * @param request
	 * @param projectId
	 *            项目ID
	 * @param entityType
	 *            1投决会2变更评议会
	 * @param online
	 *            0-线上评议会1-线下评议会
	 * @return
	 */
	@RequestMapping(value = "/selectProjectCouncil", method = RequestMethod.POST)
	public JsonResult selectProjectCouncil(HttpServletRequest request,
			@RequestParam(name = "projectId", required = false) Long projectId,
			@RequestParam(name = "entityType", required = false) Integer entityType,
			@RequestParam(name = "online", required = false) Integer online,
			@RequestParam(name = "processInstanceId", required = false) String processInstanceId) {
		Map<String, Object> columnMap = new HashMap<>();
		if (projectId != null) {
			columnMap.put("projectId", projectId);
		}
		if (entityType != null) {
			columnMap.put("entityType", entityType);
		}
		if (online != null) {
			columnMap.put("online", online);
		}
		if(processInstanceId != null && !processInstanceId.equals("")) {
			columnMap.put("PROCESS_INSTANCE_ID_", processInstanceId);
		}
		List<ProjectCouncil> selectByMap = this.service.selectByMap(columnMap);
		for (ProjectCouncil p : selectByMap) {
			Map<String, Object> columnMap1 = new HashMap<>();
			columnMap1.put("projectId", projectId);
			columnMap1.put("council_id", p.getEntityId());
			List<ProjectCouncilMember> selectByMap2 = this.projectCouncilMemberService.selectByMap(columnMap1);
			p.setProjectCouncilMemberList(selectByMap2);
		}
		return JsonResult.success(selectByMap);
	}
	/**
	 * 添加项目投决评议会，变更评审会内容
	 * 
	 * @param request
	 * @param entity
	 * @return
	 */
	/*
	 * @RequestMapping(value = "/insertProjectCouncilAll", method =
	 * RequestMethod.POST) public JsonResult
	 * insertProjectCouncilAll(HttpServletRequest request, ProjectCouncil entity) {
	 * UserEntity user = SessionUtils.getUser(request);
	 * 
	 * entity.setCouncilTime(new Date()); entity.setItcode(user.getEntityCode());
	 * entity.setResourceId(user.getResId()); boolean insert =
	 * this.service.updateById(entity); if (insert) { return JsonResult.SUCCESS; }
	 * else { return ErrorResult.INSERT_PROJECT_ERROE; } }
	 */

}
