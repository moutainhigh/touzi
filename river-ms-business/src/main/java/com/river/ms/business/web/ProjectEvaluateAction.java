package com.river.ms.business.web;

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
import com.river.ms.business.entity.ProjectEvaluate;
import com.river.ms.business.feign.serviceImpl.ToProjectImpl;
import com.river.ms.business.result.ErrorResult;
import com.river.ms.business.service.MPProjectEvaluateService;

/**
 * <p>
 * 项目实施评价记录 前端控制器
 * </p>
 *
 * @author zyb
 * @since 2017-12-21
 */
@RestController
@RequestMapping("/projectEvaluate")
public class ProjectEvaluateAction {

	@Autowired
	MPProjectEvaluateService service;
	
	@Autowired
	ToProjectImpl toProject;

	/**
	 * 实施评价添加
	 * 
	 * @param request
	 * @param entity
	 * @return
	 */
	@RequestMapping(value = "/insertProjectEvaluate", method = RequestMethod.POST)
	public JsonResult insertProjectEvaluate(HttpServletRequest request, ProjectEvaluate entity,
			@RequestParam(name = "docStr", required = false) String docStr) {
		UserEntity user = SessionUtils.getUser(request);
		entity.setItcode(user.getEntityCode());
		entity.setResourceId(user.getResId());
		Map<String, Object> headerMap = SessionUtils.getHeaderMap(request);
		Boolean insertProjectEvaluate = this.service.insertProjectEvaluate(entity, headerMap,docStr,user);
		if (insertProjectEvaluate) {
			return JsonResult.success(entity);
		}
		return ErrorResult.UPDATE_ERROR;
	}

	/**
	 * 实施评价查询
	 * 
	 * @param request
	 * @param projectId
	 * @return
	 */
	@RequestMapping(value = "/getProjectEvaluate", method = RequestMethod.POST)
	public JsonResult getProjectEvaluate(HttpServletRequest request, @RequestParam(name = "projectId") Long projectId,
			@RequestParam(name = "processInstanceId", required = false) String processInstanceId) {
		Map<String, Object> headerMap = SessionUtils.getHeaderMap(request);
		if (processInstanceId == null || processInstanceId.equals("")) {
			processInstanceId = this.toProject.getProjectProcessInstanceId(headerMap, projectId);
		}
		EntityWrapper<ProjectEvaluate> wrapper = new EntityWrapper<>();
		wrapper.eq("projectId", projectId);
		wrapper.eq("PROCESS_INSTANCE_ID_", processInstanceId);
		List<ProjectEvaluate> selectList = this.service.selectList(wrapper);
		if (selectList != null && selectList.size() > 0) {
			return JsonResult.success(selectList.get(0));
		}
		return JsonResult.SUCCESS;
	}
}
