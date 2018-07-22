package com.river.ms.project.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.river.ms.project.entity.ProjectCategoryIndex;
import com.river.ms.project.entity.ProjectCategoryIndicator;
import com.river.ms.project.result.ErrorResult;
import com.river.ms.project.service.MPProjectCategoryIndexService;
import com.river.ms.project.service.MPProjectCategoryIndicatorService;
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
@RequestMapping("/projectCategory")
public class ProjectCategoryAction {

	@Autowired
	MPProjectCategoryService service;

	@Autowired
	MPProjectCategoryIndexService projectCategoryIndexService;

	@Autowired
	MPProjectCategoryIndicatorService projectCategoryIndicatorService;

	/**
	 * 获取项目分类
	 * 
	 * @return
	 */
	@RequestMapping(value = "/getAllCategory", method = RequestMethod.POST)
	public JsonResult getAllCategory(@RequestParam(name = "parentId",required = false , defaultValue = "0") Long parentId,
			@RequestParam(name = "isAll",required = false , defaultValue = "true") Boolean isAll) {
		List<ProjectCategory> projectCategorys = this.service.getProjectCategorys(parentId,isAll);
		return JsonResult.success(projectCategorys);
	}
	
	/**
	 * 获取所有的项目类型
	 * 
	 * @return
	 */
	@RequestMapping(value = "/getCategoryByParentId", method = RequestMethod.POST)
	public JsonResult getCategoryByParentId(@RequestParam(name = "parentId",required = false , defaultValue = "0") Long parentId,
			@RequestParam(name = "isAll",required = false , defaultValue = "true") Boolean isAll) {
		List<ProjectCategory> projectCategorys = this.service.getProjectCategorys(parentId,isAll);
		List<ProjectCategory> projectCategoryList = this.service.getProjectCategoryList(projectCategorys);
		return JsonResult.success(projectCategoryList);
	}
	
	/**
	 * 获取项目分类
	 * 
	 * @return
	 */
	@RequestMapping(value = "/getAllCategorys", method = RequestMethod.POST)
	public JsonResult getAllCategorys() {
		EntityWrapper<ProjectCategory> wrapper = new EntityWrapper<>();
		wrapper.eq("isDelete", 0);
		List<ProjectCategory> projectCategorys = this.service.selectList(wrapper);
		return JsonResult.success(projectCategorys);
	}

	/**
	 * 获取投资类别
	 * 
	 * @return
	 */
	@RequestMapping(value = "/getCategory", method = RequestMethod.POST)
	public JsonResult getCategory() {
		List<ProjectCategory> categorys = this.service.getCategorys();
		return JsonResult.success(categorys);
	}

	/**
	 * 生成可研数据的模版
	 */
	@RequestMapping(value = "/getIndexAndIdicatorTemplate/categoryId/{categoryId}", method = RequestMethod.GET)
	public JsonResult getCategoryIndexAndIdicatorTemplate(HttpServletResponse response,
			@PathVariable("categoryId") Long categoryId) {
		String fileName = "可研预测数据模版";
		Map<String, Map<Class, List<List<Object>>>> dataList = new HashMap<>();
		try {
			// 评价指标
			ProjectCategoryIndex index = new ProjectCategoryIndex();
			index.setFeasibility(1);
			//index.setActual(1);
			index.setCategoryId(categoryId);
			List<ProjectCategoryIndex> projectCategoryIndex = this.projectCategoryIndexService
					.getProjectCategoryIndex(index);
			if (projectCategoryIndex != null && !projectCategoryIndex.isEmpty()) {
				List<List<Object>> indexList = new ArrayList<>();
				List<Object> indexStr = new ArrayList<>();
				indexStr.add("id");
				indexStr.add("参数");
				indexStr.add("单位");
				indexStr.add("预测数据");
				indexList.add(indexStr);
				for (ProjectCategoryIndex p : projectCategoryIndex) {
					List<Object> indexStr1 = new ArrayList<>();
					indexStr1.add(p.getProjectIndicator().getEntityId());
					indexStr1.add(p.getProjectIndicator().getIndicator());
					indexStr1.add(p.getProjectIndicator().getUnit());
					indexList.add(indexStr1);
				}
				Map<Class, List<List<Object>>> temp1 = new HashMap<>();
				temp1.put(ProjectCategoryIndex.class, indexList);
				dataList.put("评价指标数据", temp1);
			}
			// 经营数据
			ProjectCategoryIndicator indicator = new ProjectCategoryIndicator();
			indicator.setCategoryId(categoryId);
			indicator.setYear(1);
			indicator.setFeasibility(1);
			List<ProjectCategoryIndicator> projectCategoryIndicator = this.projectCategoryIndicatorService
					.getProjectCategoryIndicator(indicator);
			if (projectCategoryIndicator != null && !projectCategoryIndicator.isEmpty()) {
				List<List<Object>> indexList = new ArrayList<>();
				List<Object> indexStr = new ArrayList<>();
				indexStr.add("id");
				indexStr.add("参数");
				indexStr.add("单位");
				for (int i = 1; i <= 30; i++) {
					indexStr.add(i);
				}
				indexList.add(indexStr);
				for (ProjectCategoryIndicator p : projectCategoryIndicator) {
					List<Object> indexStr1 = new ArrayList<>();
					indexStr1.add(p.getProjectIndicator().getEntityId());
					indexStr1.add(p.getProjectIndicator().getIndicator());
					indexStr1.add(p.getProjectIndicator().getUnit());
					indexList.add(indexStr1);
				}
				Map<Class, List<List<Object>>> temp1 = new HashMap<>();
				temp1.put(ProjectCategoryIndicator.class, indexList);
				dataList.put("经营数据", temp1);
			}
			this.service.writeExcelAll(dataList, response, fileName);
		} catch (Exception e) {
			e.printStackTrace();
			return ErrorResult.INDEX_INDICATOR_TEMPLET_ERROR;
		}
		return JsonResult.SUCCESS;
	}

	/**
	 * 实际运营数据模版导出
	 */
	@RequestMapping(value = "/getActualOperationalDataTemplateAll", method = RequestMethod.GET)
	public JsonResult getActualOperationalDataTemplateAll(HttpServletResponse response, ProjectCategoryIndicator indicator) {
		String fileName = "运营数据模版";
		Map<String, Map<Class, List<List<Object>>>> dataList = new HashMap<>();
		try {
			// 经营数据
			List<ProjectCategoryIndicator> projectCategoryIndicator = this.projectCategoryIndicatorService
					.getProjectCategoryIndicator(indicator);
			if (projectCategoryIndicator != null && !projectCategoryIndicator.isEmpty()) {
				List<List<Object>> indexList = new ArrayList<>();
				List<Object> indexStr = new ArrayList<>();
				indexStr.add("id");
				indexStr.add("参数");
				indexStr.add("单位");
				indexList.add(indexStr);
				for (ProjectCategoryIndicator p : projectCategoryIndicator) {
					List<Object> indexStr1 = new ArrayList<>();
					indexStr1.add(p.getProjectIndicator().getEntityId());
					indexStr1.add(p.getProjectIndicator().getIndicator());
					indexStr1.add(p.getProjectIndicator().getUnit());
					indexList.add(indexStr1);
				}
				Map<Class, List<List<Object>>> temp1 = new HashMap<>();
				temp1.put(ProjectCategoryIndicator.class, indexList);
				dataList.put("经营数据", temp1);
			}
			this.service.writeExcelAll(dataList, response, fileName);
		} catch (Exception e) {
			e.printStackTrace();
			return ErrorResult.INDEX_INDICATOR_TEMPLET_ERROR;
		}
		return JsonResult.SUCCESS;
	}
}
