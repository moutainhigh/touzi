package com.river.ms.business.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.river.core.result.JsonResult;
import com.river.ms.business.entity.ProjectFlowStart;
import com.river.ms.business.service.MPProjectFlowStartService;

/**
 * <p>
 * 启动流程配置 前端控制器
 * </p>
 *
 * @author zyb
 * @since 2017-12-20
 */
@RestController
@RequestMapping("/projectFlowStart")
public class ProjectFlowStartAction {

	@Autowired
	MPProjectFlowStartService service;

	/**
	 * 添加或者修改流程实例配置
	 * 
	 * @param request
	 * @param entity
	 * @return
	 */
	@RequestMapping(value = "/insertOrUpadateProjectFlowStart", method = RequestMethod.POST)
	public JsonResult insertOrUpadateProjectFlowStart(HttpServletRequest request, @Valid ProjectFlowStart entity,
			BindingResult res) {
		if (res.hasErrors()) {
			return JsonResult.BindingError(res);
		}
		Long id = new Long(-1);
		if (entity.getEntityId() != null) {
			id = entity.getEntityId();
		}
		Long exist = this.service.isExist(id, entity.getFlowId(), entity.getGroupCategoryId(), entity.getType());
		if(exist == 0) {
			boolean insertOrUpdate = this.service.insertOrUpdate(entity);
			return JsonResult.success(insertOrUpdate);
		} else {
			return JsonResult.CODE_ISEXIST;
		}
	}

	/**
	 * 获取流程实例配置
	 * 
	 * @param request
	 * @param entityId
	 *            流程实例配置ID
	 * @param groupCategoryId
	 *            项目类型ID
	 * @param type
	 *            流程类型 1-立项，2-可研，3-投决，4-投资价值评估，5，变更
	 * @return
	 */
	@RequestMapping(value = "/getProjectFlowStart", method = RequestMethod.POST)
	public JsonResult getProjectFlowStart(HttpServletRequest request,
			@RequestParam(name = "entityId", required = false) Long entityId,
			@RequestParam(name = "groupCategoryId", required = false) Long groupCategoryId,
			@RequestParam(name = "type", required = false) Integer type) {
		List<ProjectFlowStart> projectFlowStart = this.service.getProjectFlowStart(entityId, groupCategoryId, type);
		return JsonResult.success(projectFlowStart);
	}
	
	@RequestMapping(value = "/deleteProjectFlowStart", method = RequestMethod.POST)
	public JsonResult deleteProjectFlowStart(HttpServletRequest request,@RequestParam("entityId") Long entityId) {
		boolean deleteById = this.service.deleteById(entityId);
		return JsonResult.success(deleteById);
	}
	
	@RequestMapping(value = "/selectProjectFlowStart", method = RequestMethod.POST)
	public JsonResult selectProjectFlowStart(HttpServletRequest request,
			@RequestParam(name = "groupCategoryId", required = false) Long groupCategoryId,
			@RequestParam(name = "type", required = false) Integer type) {
		List<ProjectFlowStart> projectFlowStart = this.service.selectProjectFlowStart(groupCategoryId, type);
		return JsonResult.success(projectFlowStart);
	}
}
