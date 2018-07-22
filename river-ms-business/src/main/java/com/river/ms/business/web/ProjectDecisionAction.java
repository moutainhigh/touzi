package com.river.ms.business.web;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.river.core.entity.UserEntity;
import com.river.core.result.JsonResult;
import com.river.core.utils.SessionUtils;
import com.river.ms.business.entity.ProjectDecision;
import com.river.ms.business.entity.ProjectPartner;
import com.river.ms.business.result.ErrorResult;
import com.river.ms.business.service.MPProjectDecisionService;

/**
 * <p>
 * 项目投决 前端控制器
 * </p>
 *
 * @author zyb
 * @since 2017-12-04
 */
@RestController
@RequestMapping("/projectDecision")
public class ProjectDecisionAction {

	@Autowired
	MPProjectDecisionService service;

	/**
	 * 添加项目投决
	 * 
	 * @param request
	 * @param entity
	 * @return
	 */
	@RequestMapping(value = "/insertProjectDecision", method = RequestMethod.POST)
	public JsonResult insertProjectDecision(HttpServletRequest request, ProjectDecision entity,
			@RequestParam(name = "str", required = false) String str,
			@RequestParam(name = "docStr", required = false) String docStr,
			@RequestParam(name = "partnerStr", required = false) String partner,
			@RequestParam(name = "partnerMemberStr", required = false) String partnerMember) {
		entity.setState(0);
		UserEntity user = SessionUtils.getUser(request);
		entity.setItcode(user.getEntityCode());
		entity.setResourceId(user.getResId());
		entity.setCreateTime(new Date());
		entity.setOnline(0);
		Map<String, Object> headerMap = SessionUtils.getHeaderMap(request);
		boolean insert = this.service.insertProjectDecision(entity, headerMap, str, docStr, user, partner,
				partnerMember);
		if (insert) {
			return JsonResult.success(entity);
		} else {
			return ErrorResult.INSERT_PROJECT_ERROE;
		}
	}

	/**
	 * 打回投決资料上传
	 * 
	 * @param request
	 * @param entity
	 * @return
	 */
	@RequestMapping(value = "/updateProjectDecision", method = RequestMethod.POST)
	public JsonResult updateProjectDecision(HttpServletRequest request, ProjectDecision entity,
			@RequestParam(name = "docStr", required = false) String docStr) {
		Boolean updateProjectDecision = this.service.updateProjectDecision(request, entity, docStr);
		if (updateProjectDecision) {
			return JsonResult.success(entity);
		}
		return ErrorResult.UPDATE_ERROR;
	}

	/**
	 * 获取投决资料
	 * 
	 * @param request
	 * @param entity
	 * @return
	 */
	@RequestMapping(value = "/getProjectDecision", method = RequestMethod.POST)
	public JsonResult getProjectDecision(HttpServletRequest request, @RequestParam(name = "projectId") Long projectId,
			@RequestParam(name = "processInstanceId", required = false) String processInstanceId) {
		EntityWrapper<ProjectDecision> wrapper = new EntityWrapper<>();
		wrapper.eq("projectId", projectId);
		if (processInstanceId != null && !processInstanceId.equals("")) {
			wrapper.eq("PROCESS_INSTANCE_ID_", processInstanceId);
		}
		List<ProjectDecision> selectList = this.service.selectList(wrapper);
		return JsonResult.success(selectList);
	}

}
