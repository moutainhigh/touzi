package com.river.ms.project.management.controller;

import java.util.ArrayList;
import java.util.Date;
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

import com.river.core.helper.StringHelper;
import com.river.core.result.JsonResult;
import com.river.ms.project.entity.ProjectCategory;
import com.river.ms.project.result.ErrorResult;
import com.river.ms.project.service.MPProjectCategoryService;

/**
 * <p>
 * 项目分类 前端控制器
 * </p>
 *
 * @author zyb
 * @since 2017-12-04
 */
@RestController
@RequestMapping("/projectCategoryManagement")
public class ProjectCategoryManagementAction {

	@Autowired
	MPProjectCategoryService service;

	/**
	 * 添加投资类别或者项目类型
	 * 
	 * @param response
	 * @param entity
	 * @param res
	 * @return
	 */
	@RequestMapping(value = "/insertOrUpdate", method = RequestMethod.POST)
	public JsonResult insertOrUpdate(HttpServletResponse response, @Valid ProjectCategory entity, BindingResult res) {
		if (res.hasErrors()) {
			return JsonResult.BindingError(res);
		}
		Long id = new Long(-1);
		if (entity.getEntityId() != null) {
			id = entity.getEntityId();
		}
		Long exist = this.service.exist(id, entity.getEntityCode());
		if (exist == 0) {
			if (entity.getEntityId() != null) {
				entity.setUpdateTime(new Date());
			} else {
				entity.setCreateTime(new Date());
				entity.setEntityGUID(StringHelper.generateGUID());
			}
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
	 * 删除项目类型
	 * 
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public JsonResult delete(HttpServletRequest request, @PathVariable(value = "id") Long id) {
		ProjectCategory entity = new ProjectCategory();
		entity.setEntityId(id);
		entity.setIsDelete(1);
		entity.setDeleteTime(new Date());
		boolean updateById = this.service.updateById(entity);
		return JsonResult.success(updateById);
	}

	@RequestMapping(value = "/deleteAll", method = RequestMethod.POST)
	public JsonResult deleteAll(HttpServletRequest request,
			@RequestParam("projectCategoryIds[]") List<Long> projectCategoryIds) {
		List<ProjectCategory> projectCategorys = new ArrayList<>();
		for (Long id : projectCategoryIds) {
			ProjectCategory entity = new ProjectCategory();
			entity.setEntityId(id);
			entity.setIsDelete(1);
			entity.setDeleteTime(new Date());
			projectCategorys.add(entity);
		}
		boolean updateById = true;
		if (projectCategorys != null && projectCategorys.size() > 0) {
			updateById = this.service.updateBatchById(projectCategorys);
		}
		return JsonResult.success(updateById);
	}
}
