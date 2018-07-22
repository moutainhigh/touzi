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

import com.river.core.entity.UserEntity;
import com.river.core.result.JsonResult;
import com.river.core.utils.SessionUtils;
import com.river.ms.business.entity.ProjectCondition;
import com.river.ms.business.entity.ProjectOpinionCondition;
import com.river.ms.business.result.ErrorResult;
import com.river.ms.business.service.MPProjectConditionService;
import com.river.ms.business.service.impl.ProjectOpinionConditionServiceDiy;

/**
 * <p>
 * 项目前置条件（赋能群确认) 前端控制器
 * </p>
 *
 * @author zyb
 * @since 2017-12-04
 */
@RestController
@RequestMapping("/projectCondition")
public class ProjectConditionAction {

	@Autowired
	MPProjectConditionService service;

	@Autowired
	ProjectOpinionConditionServiceDiy projectOpinionService;
	/**
	 * 前置条件确认
	 * 
	 * @param request
	 * @param entity
	 * @return
	 */
	@RequestMapping(value = "/affirmProjectCondition", method = RequestMethod.POST)
	public JsonResult affirmProjectCondition(HttpServletRequest request
			, @RequestParam(name="projectId")Long projectId){
		UserEntity user = SessionUtils.getUser(request);
		Boolean insertProjectConditions = this.service.insertProjectConditions(request,projectId);
		if (insertProjectConditions) {
			return JsonResult.SUCCESS;
		} else {
			return ErrorResult.INSERT_PROJECT_ERROE;
		}
	}
	
	/**
	 * 获取前置条件
	 * @param request
	 * @param projectId
	 * @return
	 */
	@RequestMapping(value = "/getProjectCondition", method = RequestMethod.POST)
	public JsonResult getProjectCondition(HttpServletRequest request, @RequestParam(name="projectId")Long projectId) {
		Map<String, Object> columnMap = new HashMap<>();
		columnMap.put("projectId", projectId);
		List<ProjectCondition> selectByMap = this.service.selectByMap(columnMap);
		return JsonResult.success(selectByMap);
	}
	
	/**
	 * 插入前置条件
	 * @param request
	 * @param projectCondition
	 * @return
	 */
	@RequestMapping(value = "/insertProjectCondition", method = RequestMethod.POST)
	public JsonResult insertProjectCondition(HttpServletRequest request, ProjectCondition projectCondition) {
		UserEntity user = SessionUtils.getUser(request);
		projectCondition.setItcode(user.getEntityCode());
		projectCondition.setResourceId(user.getResId());
		projectCondition.setCreateTime(new Date());
		boolean insert = this.service.insert(projectCondition);
		if(insert) {
			if(projectCondition.getOpinionConditionId() != null) {
				ProjectOpinionCondition opinion = this.projectOpinionService.selectById(projectCondition.getOpinionConditionId());
				opinion.setType(1);
				this.projectOpinionService.updateById(opinion);
			}
		}
		return JsonResult.success(projectCondition);
	}
	
	/**
	 * 删除前置条件
	 * @param request
	 * @param projectCondition
	 * @return
	 */
	@RequestMapping(value = "/removeProjectCondition", method = RequestMethod.POST)
	public JsonResult removeProjectCondition(HttpServletRequest request, @RequestParam(name="entityId")Long entityId) {
		ProjectCondition condition = this.service.selectById(entityId);
		if(condition.getOpinionConditionId() != null) {
			ProjectOpinionCondition opinion = this.projectOpinionService.selectById(condition.getOpinionConditionId());
			opinion.setType(0);
			this.projectOpinionService.updateById(opinion);
		}
		boolean deleteById = this.service.deleteById(entityId);
		return JsonResult.success(deleteById);
	}
}
