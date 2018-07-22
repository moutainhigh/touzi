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

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.river.core.result.JsonResult;
import com.river.ms.business.entity.ProjectBbs;
import com.river.ms.business.entity.ProjectFlow;
import com.river.ms.business.entity.ProjectFlowStart;
import com.river.ms.business.service.MPProjectFlowService;
import com.river.ms.business.service.MPProjectFlowStartService;

/**
 * <p>
 * 流程定义表 前端控制器
 * </p>
 *
 * @author zyb
 * @since 2017-12-20
 */
@RestController
@RequestMapping("/projectFlow")
public class ProjectFlowAction {

	@Autowired
	MPProjectFlowService service;

	@Autowired
	MPProjectFlowStartService projectFlowStartService;

	/**
	 * 添加流程实例
	 * 
	 * @param request
	 * @param entity
	 * @return
	 */
	@RequestMapping(value = "/insertOrUpdateProjectFlow", method = RequestMethod.POST)
	public JsonResult insertOrUpdateProjectFlow(HttpServletRequest request, @Valid ProjectFlow entity,
			BindingResult res) {

		if (res.hasErrors()) {
			return JsonResult.BindingError(res);
		}
		Long id = new Long(-1);
		if (entity.getEntityId() != null) {
			id = entity.getEntityId();
		}
		Long exist = this.service.isExist(id, entity.getEntityCode());
		if (exist == 0) {
			boolean insertOrUpdate = this.service.insertOrUpdate(entity);
			return JsonResult.success(insertOrUpdate);
		} else {
			return JsonResult.CODE_ISEXIST;
		}
	}

	@RequestMapping(value = "/deleteProjectFlow", method = RequestMethod.POST)
	public JsonResult deleteProjectFlow(HttpServletRequest request, @RequestParam("entityId") Long entityId) {
		boolean deleteById = this.service.deleteById(entityId);
		return JsonResult.success(deleteById);
	}

	/**
	 * 根据ID获取流程实例对象
	 * 
	 * @param request
	 * @param entityId
	 * @return
	 */
	@RequestMapping(value = "/getAllProjectFlow", method = RequestMethod.POST)
	public JsonResult getAllProjectFlow(HttpServletRequest request,
			@RequestParam(value = "page", required = false, defaultValue = "1") int page,
			@RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize) {
		PageHelper.startPage(page, pageSize);
		EntityWrapper<ProjectFlow> wrapper = new EntityWrapper<>();
		List<ProjectFlow> selectList = this.service.selectList(wrapper);
		PageInfo<ProjectFlow> pageInfo = new PageInfo<ProjectFlow>(selectList, 5);
		return JsonResult.successPage(selectList,pageInfo);
	}

	@RequestMapping(value = "/getFlowId", method = RequestMethod.POST)
	public Long getFlowId(HttpServletRequest request, @RequestParam("groupCategoryId") Long groupCategoryId,
			@RequestParam("type") Integer type) {
		Long result = new Long(0);
		List<ProjectFlowStart> projectFlowStart = projectFlowStartService.getProjectFlowStart(null, groupCategoryId,
				type);
		if (projectFlowStart != null && projectFlowStart.size() > 0) {
			result = projectFlowStart.get(0).getFlowId();
		}
		return result;
	}
}
