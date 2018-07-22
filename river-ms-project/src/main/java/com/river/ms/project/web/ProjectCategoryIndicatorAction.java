package com.river.ms.project.web;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONArray;
import com.river.core.result.JsonResult;
import com.river.ms.project.entity.ProjectCategoryIndicator;
import com.river.ms.project.service.MPProjectCategoryIndicatorService;

/**
 * <p>
 * 行业公司项目类别对应经营数据：暂时没有考虑预测，实际，可行，投后等 前端控制器
 * </p>
 *
 * @author zyb
 * @since 2017-12-04
 */
@RestController
@RequestMapping("/projectCategoryIndicator")
public class ProjectCategoryIndicatorAction {

	@Autowired
	MPProjectCategoryIndicatorService service;

	/**
	 * 获取项目对应的经营数据
	 * 
	 * @return
	 */
	@RequestMapping(value = "/getProjectCategoryIndicator", method = RequestMethod.POST)
	public JsonResult getProjectCategoryIndicator(ProjectCategoryIndicator entity) {
		List<ProjectCategoryIndicator> projectCategoryIndicator = this.service.getProjectCategoryIndicator(entity);
		return JsonResult.success(projectCategoryIndicator);
	}

	/**
	 * 添加项目对应的经营数据
	 * 
	 * @return
	 */
	@RequestMapping(value = "/insertProjectCategoryIndicator", method = RequestMethod.POST)
	public JsonResult insertProjectCategoryIndicator(HttpServletRequest request,
			@RequestParam(name = "str", required = false) String str) {
		List<ProjectCategoryIndicator> entity = new ArrayList<>();
		entity = JSONArray.parseArray(str, ProjectCategoryIndicator.class);
		boolean insertBatch = this.service.insertProjectCategoryIndicator(entity);
		return JsonResult.success(insertBatch);
	}
}
