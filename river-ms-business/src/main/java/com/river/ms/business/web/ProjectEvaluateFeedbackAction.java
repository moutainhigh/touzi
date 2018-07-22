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

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.river.core.entity.UserEntity;
import com.river.core.result.JsonResult;
import com.river.core.utils.SessionUtils;
import com.river.ms.business.entity.ProjectEvaluateFeedback;
import com.river.ms.business.entity.ProjectEvaluateRectify;
import com.river.ms.business.result.ErrorResult;
import com.river.ms.business.service.MPProjectEvaluateFeedbackService;
import com.river.ms.business.service.MPProjectEvaluateRectifyService;

/**
 * <p>
 * 项目评价反馈 前端控制器
 * </p>
 *
 * @author zyb
 * @since 2017-12-22
 */
@RestController
@RequestMapping("/projectEvaluateFeedback")
public class ProjectEvaluateFeedbackAction {

	@Autowired
	MPProjectEvaluateFeedbackService service;

	@Autowired
	MPProjectEvaluateRectifyService rectifyService;

	@RequestMapping(value = "/insertProjectEvaluateFeedback", method = RequestMethod.POST)
	public JsonResult insertProjectEvaluateFeedback(HttpServletRequest request, ProjectEvaluateFeedback entity,
			@RequestParam(name = "docStr", required = false) String docStr) {
		UserEntity user = SessionUtils.getUser(request);
		Map<String, Object> headerMap = SessionUtils.getHeaderMap(request);
		entity.setFeedbackItcode(user.getEntityCode());
		entity.setCreateTime(new Date());
		boolean insert = this.service.insertEvaluateFeedback(entity, headerMap,request,docStr);
		if (insert) {
			return JsonResult.success(entity);
		}
		return ErrorResult.UPDATE_ERROR;
	}

	@RequestMapping(value = "/getProjectEvaluateFeedback", method = RequestMethod.POST)
	public JsonResult getProjectEvaluateFeedback(HttpServletRequest request,
			@RequestParam(name = "projectId") Long projectId,
			@RequestParam(name = "evaluateId", required = false) Long evaluateId,
			@RequestParam(name = "type", required = false, defaultValue = "true") boolean type) {
		EntityWrapper<ProjectEvaluateFeedback> wrapper = new EntityWrapper<>();
		wrapper.eq("projectId", projectId);
		wrapper.eq("evaluate_Id", evaluateId);
		List<ProjectEvaluateFeedback> selectList = this.service.selectList(wrapper);
		if (selectList != null && selectList.size() > 0) {
			for (ProjectEvaluateFeedback result : selectList) {
				if (type) {
					Map<String, Object> columnMap = new HashMap<>();
					columnMap.put("projectId", result.getProjectId());
					columnMap.put("evaluate_id", result.getEvaluateId());
					columnMap.put("feedback_id", result.getEntityId());
					List<ProjectEvaluateRectify> selectByMap = rectifyService.selectByMap(columnMap);
					if (selectByMap != null && selectByMap.size() > 0) {
						result.setRectify(selectByMap.get(0));
					}
				}
			}
		}
		return JsonResult.success(selectList);
	}
}
