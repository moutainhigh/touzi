package com.river.ms.business.web;

import java.util.Date;
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
import com.river.ms.business.entity.ProjectOpinion;
import com.river.ms.business.feign.serviceImpl.ToProjectImpl;
import com.river.ms.business.result.ErrorResult;
import com.river.ms.business.service.MPProjectOpinionConditionService;
import com.river.ms.business.service.MPProjectOpinionService;
import com.river.ms.business.service.MPProjectTodoService;

/**
 * <p>
 * 专家意见 前端控制器
 * </p>
 *
 * @author zyb
 * @since 2017-12-04
 */
@RestController
@RequestMapping("/projectOpinion")
public class ProjectOpinionAction {

	@Autowired
	MPProjectOpinionService service;

	@Autowired
	MPProjectOpinionConditionService conditionService;

	@Autowired
	MPProjectTodoService projectTodoService;

	@Autowired
	ToProjectImpl toProject;

	private Boolean isDeleteExpertToDo = true;

	/**
	 * 添加专家评议
	 * 
	 * @param request
	 * @param entity
	 * @return
	 */
	@RequestMapping(value = "/insertProjectOpinion", method = RequestMethod.POST)
	public JsonResult insertProjectOpinion(HttpServletRequest request, ProjectOpinion entity) {
		if (entity == null)
			return JsonResult.failure("opinion is null");
		if (entity.getProjectId() == null)
			return JsonResult.failure("projectId is null");
		if (entity.getStage() == null)
			return JsonResult.failure("stage is null");
		UserEntity user = SessionUtils.getUser(request);
		if (user == null)
			return JsonResult.failure("user is null");
		Map<String, Object> headerMap = SessionUtils.getHeaderMap(request);
		String projectProcessInstanceId = toProject.getProjectProcessInstanceId(headerMap, entity.getProjectId());
		entity.setItcode(user.getEntityCode());
		entity.setResourceId(user.getResId());
		entity.setCreateTime(new Date());
		entity.setPROCESS_INSTANCE_ID_(projectProcessInstanceId);
		boolean insert = this.service.insert(entity);
		if (insert) {
			if (this.getIsDeleteExpertToDo() && insert) {
				this.projectTodoService.deleteTodo(entity.getProjectId(), null, user.getResId(), user.getEntityCode(), 1);
			}
			return JsonResult.success(entity);
		}
		return ErrorResult.INSERT_PROJECT_ERROE;
	}

	/**
	 * 查询专家意见
	 * 
	 * @param request
	 * @param entity
	 * @param conditions
	 * @return
	 */
	@RequestMapping(value = "/selectProjectOpinion", method = RequestMethod.POST)
	public JsonResult selectProjectOpinion(HttpServletRequest request,
			@RequestParam(value = "projectId") Long projectId, @RequestParam(value = "stage") String stage) {
		List<ProjectOpinion> projectOpinions = this.service.getProjectOpinions(projectId, stage);
		return JsonResult.success(projectOpinions);
	}

	public Boolean getIsDeleteExpertToDo() {
		return isDeleteExpertToDo;
	}

}
