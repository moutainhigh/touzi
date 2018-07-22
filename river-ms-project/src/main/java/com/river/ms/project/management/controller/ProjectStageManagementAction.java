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
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.river.core.result.JsonResult;
import com.river.ms.project.entity.ProjectStage;
import com.river.ms.project.result.ErrorResult;
import com.river.ms.project.service.MPProjectStageService;

/**
 * <p>
 * 项目阶段 前端控制器
 * </p>
 *
 * @author zyb
 * @since 2017-12-04
 */
@RestController
@RequestMapping("/projectStageManagement")
public class ProjectStageManagementAction {

	@Autowired
	MPProjectStageService service;

	/**
	 * 添加或者修改节点
	 * 
	 * @param response
	 * @param entity
	 * @param res
	 * @return
	 */
	@RequestMapping(value = "/insertOrUpdate", method = RequestMethod.POST)
	public JsonResult insertOrUpdate(HttpServletResponse response, @Valid ProjectStage entity, BindingResult res) {
		if (res.hasErrors()) {
			return JsonResult.BindingError(res);
		}
		Long id = new Long(-1);
		if (entity.getEntityId() != null) {
			id = entity.getEntityId();
		}
		Long exist = this.service.exist(id, entity.getEntityCode());
		if (exist == 0) {
			boolean insertOrUpdate = this.service.insertOrUpdate(entity);
			if (insertOrUpdate) {
				return JsonResult.success(entity);
			} else {
				return JsonResult.failure("操作失败！");
			}
		} else {
			return ErrorResult.CODE_EXIST;
		}
	}

	/**
	 * 删除节点
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
	
	@RequestMapping(value = "/select", method = RequestMethod.POST)
	public JsonResult select(HttpServletRequest request) {
		EntityWrapper<ProjectStage> wrapper = new EntityWrapper<>();
		List<ProjectStage> selectList = this.service.selectList(wrapper);
		return JsonResult.success(selectList);
	}

}
