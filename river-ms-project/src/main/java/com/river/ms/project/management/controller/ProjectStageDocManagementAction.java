package com.river.ms.project.management.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.river.core.result.JsonResult;
import com.river.ms.project.entity.ProjectStage;
import com.river.ms.project.entity.ProjectStageDoc;
import com.river.ms.project.result.ErrorResult;
import com.river.ms.project.service.MPProjectStageDocService;

/**
 * <p>
 * 项目阶段文档匹配 前端控制器
 * </p>
 *
 * @author zyb
 * @since 2017-12-04
 */
@RestController
@RequestMapping("/projectStageDocManagement")
public class ProjectStageDocManagementAction {

	@Autowired
	MPProjectStageDocService service;

	/**
	 * 添加节点文档
	 * 
	 * @param response
	 * @param entity
	 * @param res
	 * @return
	 */
	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public JsonResult insert(HttpServletResponse response, ProjectStageDoc entity) {
		boolean insert = this.service.insert(entity);
		return JsonResult.success(insert);
	}

	/**
	 * 删除节点文档
	 * 
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public JsonResult delete(HttpServletRequest request, @PathVariable(value = "id") Long id) {
		boolean deleteById = this.service.deleteById(id);
		return JsonResult.success(deleteById);
	}

	/**
	 * 查询节点文档
	 * 
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/select", method = RequestMethod.POST)
	public JsonResult select(HttpServletRequest request, @RequestParam(value = "stageId") Long stageId,
			@RequestParam(value = "categoryGroupId",required=false) Long categoryGroupId) {
		EntityWrapper<ProjectStageDoc> wrapper = new EntityWrapper<>();
		wrapper.eq("stageId", stageId);
		if(categoryGroupId != null) {
			wrapper.eq("categoryGroupId", categoryGroupId);
		}
		List<ProjectStageDoc> selectList = this.service.selectList(wrapper);
		return JsonResult.success(selectList);
	}
}
