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
import com.river.ms.project.entity.ProjectCategoryIndex;
import com.river.ms.project.service.MPProjectCategoryIndexService;

/**
 * <p>
 * 行业公司项目类别对应评价指标 前端控制器
 * </p>
 *
 * @author zyb
 * @since 2017-12-04
 */
@RestController
@RequestMapping("/projectCategoryIndex")
public class ProjectCategoryIndexAction {

	@Autowired
	MPProjectCategoryIndexService service;

	/**
	 * 获取项目对应的评价指标
	 * 
	 * @return
	 */
	@RequestMapping(value = "/getProjectCategoryIndex", method = RequestMethod.POST)
	public JsonResult getProjectCategoryIndex(ProjectCategoryIndex entity) {
		List<ProjectCategoryIndex> projectCategoryIndex = this.service.getProjectCategoryIndex(entity);
		return JsonResult.success(projectCategoryIndex);
	}

	/**
	 * 添加项目对应的评价指标
	 * 
	 * @return
	 */
	@RequestMapping(value = "/insertProjectCategoryIndex", method = RequestMethod.POST)
	public JsonResult insertProjectCategoryIndex(HttpServletRequest request,
			@RequestParam(name = "str", required = false) String str) {
		List<ProjectCategoryIndex> entity = new ArrayList<>();
		entity = JSONArray.parseArray(str, ProjectCategoryIndex.class);
		boolean insertBatch = this.service.insertProjectCategoryIndex(entity);
		return JsonResult.success(insertBatch);
	}

}
