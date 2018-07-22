package com.river.ms.project.management.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.river.core.result.JsonResult;
import com.river.ms.project.entity.ProjectCategory;
import com.river.ms.project.entity.ProjectCategoryGroup;
import com.river.ms.project.result.ErrorResult;
import com.river.ms.project.service.MPProjectCategoryGroupService;
import com.river.ms.project.service.MPProjectCategoryService;

/**
 * <p>
 * 行业公司项目分类 前端控制器
 * </p>
 *
 * @author zyb
 * @since 2017-12-04
 */
@RestController
@RequestMapping("/projectCategoryGroupManagement")
public class ProjectCategoryGroupManagementAction {

	@Autowired
	MPProjectCategoryGroupService service;

	@Autowired
	MPProjectCategoryService categoryService;

	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public JsonResult insert(HttpServletResponse response,
			@RequestParam(name = "categoryIds[]") List<Long> categoryIds,
			@RequestParam(name = "categoryId") Long categoryId, 
			@RequestParam(name = "groupCode") String groupCode) {
		EntityWrapper<ProjectCategory> categoryWrapper = new EntityWrapper<>();
		categoryWrapper.in("entityId", categoryIds);
		List<ProjectCategory> categorys = this.categoryService.selectList(categoryWrapper);
		if (categorys != null && categorys.size() > 0) {
			Boolean insertProjectCategoryGroup = this.service.insertProjectCategoryGroup(categorys, categoryId, groupCode);
			return JsonResult.success(insertProjectCategoryGroup);
		} else {
			return ErrorResult.CATEGORY_ERROR;
		}
	}
	
	/**
	 * 删除项目类型
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public JsonResult delete(HttpServletRequest request,@PathVariable(value = "id") Long id) {
		ProjectCategoryGroup entity = new ProjectCategoryGroup();
		entity.setEntityId(id);
		entity.setIsDelete(1);
		entity.setDeleteTime(new Date());
		boolean updateById = this.service.updateById(entity);
		return JsonResult.success(updateById);
	}
}
