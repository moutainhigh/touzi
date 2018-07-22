package com.river.ms.business.web;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONArray;
import com.river.core.entity.UserEntity;
import com.river.core.result.JsonResult;
import com.river.core.utils.SessionUtils;
import com.river.ms.business.entity.ProjectConditionFeedback;
import com.river.ms.business.entity.ProjectDocument;
import com.river.ms.business.result.ErrorResult;
import com.river.ms.business.service.MPProjectConditionFeedbackService;
import java.util.Map;

/**
 * <p>
 * 前置条件落实反馈 前端控制器
 * </p>
 *
 * @author zyb
 * @since 2017-12-04
 */
@RestController
@RequestMapping("/projectConditionFeedback")
public class ProjectConditionFeedbackAction {

	@Autowired
	MPProjectConditionFeedbackService service;

	/**
	 * 
	 * @param request
	 * @param projectId
	 * @param type
	 * @return
	 */
	@RequestMapping(value = "/getByProjectId", method = RequestMethod.POST)
	public JsonResult getConditionFeebackByProjectId(HttpServletRequest request,
			@RequestParam(name = "projectId") Integer projectId,
			@RequestParam(name = "type",required=false) Integer type) {
		List<ProjectConditionFeedback> list = this.service.getConditionFeedbackById(projectId,type);
		return JsonResult.success(list);
	}
	/**
	 * 
	 * @param request
	 * @param projectId
	 * @param type
	 * @return
	 */
	@RequestMapping(value = "/getImplByProjectId", method = RequestMethod.POST)
	public JsonResult getConditionFeebackImplByProjectId(HttpServletRequest request,
			@RequestParam(name = "projectId") Integer projectId,
			@RequestParam(name = "type",required=false) Integer type) {
		List<ProjectConditionFeedback> list = this.service.getConditionFeedbackByProjectId(projectId,type);
		return JsonResult.success(list);
	}
	/**
	 * 添加前置条件落实反馈
	 * 
	 * @param request
	 * @param entity
	 * @return
	 */
	@RequestMapping(value = "/insertProjectConditionFeedback", method = RequestMethod.POST)
	public JsonResult insertProjectConditionFeedback(HttpServletRequest request,
			@RequestParam(name = "str", required = false) String str) {
		List<ProjectConditionFeedback> entitys = JSONArray.parseArray(str, ProjectConditionFeedback.class);
		UserEntity user = SessionUtils.getUser(request);
		Map<String, Object> columnMap = null;
		try {
			for (ProjectConditionFeedback entity : entitys) {
				entity.setFeedbackItcode(user.getEntityCode());
				entity.setFeedbackResourceId(user.getResId());
				columnMap = new HashMap<String, Object>();
				columnMap.put("projectId", entity.getProjectId());
				columnMap.put("conditionId", entity.getConditionId());
				this.service.deleteByMap(columnMap);

				this.service.insert(entity);
			}
			return JsonResult.SUCCESS;
		} catch (Exception ex) {
			return JsonResult.failure(ex.getMessage());
		}
	}

	/**
	 * 添加前置条件落实反馈审核
	 * 
	 * @param request
	 * @param projectId
	 * @return
	 */
	@RequestMapping(value = "/insertProjectConditionFeedbackAudit", method = RequestMethod.POST)
	public JsonResult insertProjectConditionFeedbackAudit(HttpServletRequest request,
			ProjectConditionFeedback entitys) {
		UserEntity user = SessionUtils.getUser(request);
		entitys.setItcode(user.getEntityCode());
		entitys.setResourceId(user.getResId());
		boolean updateById = this.service.updateById(entitys);
		if (updateById) {
			return JsonResult.SUCCESS;
		} else {
			return ErrorResult.INSERT_PROJECT_ERROE;
		}
	}
}
