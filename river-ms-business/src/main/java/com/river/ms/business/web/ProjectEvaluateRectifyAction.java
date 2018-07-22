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
import com.river.ms.business.entity.ProjectEvaluateRectify;
import com.river.ms.business.result.ErrorResult;
import com.river.ms.business.service.MPProjectEvaluateRectifyService;

/**
 * <p>
 * 项目反馈/整改审核 前端控制器
 * </p>
 *
 * @author zyb
 * @since 2017-12-22
 */
@RestController
@RequestMapping("/projectEvaluateRectify")
public class ProjectEvaluateRectifyAction {

	@Autowired
	MPProjectEvaluateRectifyService service;

	@RequestMapping(value = "/insertProjectEvaluateRectify", method = RequestMethod.POST)
	public JsonResult insertProjectEvaluateRectify(HttpServletRequest request, ProjectEvaluateRectify entity) {
		UserEntity user = SessionUtils.getUser(request);
		Map<String, Object> headerMap = SessionUtils.getHeaderMap(request);
		entity.setFeedbackAuditor(user.getEntityCode());
		entity.setCreateTime(new Date());
		boolean insert = this.service.insertProjectEvaluateRectify(entity, headerMap);
		if (insert) {
			return JsonResult.success(entity);
		}
		return ErrorResult.UPDATE_ERROR;
	}
	
	@RequestMapping(value = "/getProjectEvaluateRectify", method = RequestMethod.POST)
	public JsonResult getProjectEvaluateRectify(HttpServletRequest request, @RequestParam(name = "projectId") Long projectId) {
		Map<String, Object> columnMap = new HashMap<>();
		columnMap.put("projectId", projectId);
		List<ProjectEvaluateRectify> selectByMap = this.service.selectByMap(columnMap);
		return JsonResult.success(selectByMap);
	}
}
