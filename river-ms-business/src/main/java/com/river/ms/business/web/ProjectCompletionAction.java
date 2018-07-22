package com.river.ms.business.web;

import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.river.core.entity.UserEntity;
import com.river.core.result.JsonResult;
import com.river.core.utils.SessionUtils;
import com.river.ms.business.entity.ProjectCompletion;
import com.river.ms.business.result.ErrorResult;
import com.river.ms.business.service.MPProjectCompletionService;

/**
 * <p>
 * 项目竣工/结项申请 前端控制器
 * </p>
 *
 * @author zyb
 * @since 2017-12-04
 */
@RestController
@RequestMapping("/projectCompletion")
public class ProjectCompletionAction {

	@Autowired
	MPProjectCompletionService service;

	/**
	 * 项目竣工、结项申请添加
	 * 
	 * @param request
	 * @param entity
	 * @return
	 */
	@RequestMapping(value = "/insertProjectCompletion", method = RequestMethod.POST)
	public JsonResult insertProjectCompletion(HttpServletRequest request, 
			ProjectCompletion entity) {
		entity.setState(0);
		entity.setCreateTime(new Date());
		UserEntity user = SessionUtils.getUser(request);
		entity.setItcode(user.getEntityCode());
		entity.setResourceId(user.getResId());
		Map<String, Object> headerMap = SessionUtils.getHeaderMap(request);
		Boolean insert = this.service.insertProjectCompletion(entity, headerMap);
		if (insert) {
			return JsonResult.success(entity);
		} else {
			return ErrorResult.INSERT_PROJECT_ERROE;
		}
	}

	/**
	 * 设置竣工结项申请状态
	 * 
	 * @param request
	 * @param entityId
	 * @param state
	 * @return
	 */
	@RequestMapping(value = "/setProjectCompletionState", method = RequestMethod.POST)
	public Boolean setProjectCompletionState(HttpServletRequest request, Long entityId, Integer state) {
		Map<String, Object> headerMap = SessionUtils.getHeaderMap(request);
		Boolean setProjectCompletionState = this.service.setProjectCompletionState(entityId, state, headerMap);
		return setProjectCompletionState;
	}

}
