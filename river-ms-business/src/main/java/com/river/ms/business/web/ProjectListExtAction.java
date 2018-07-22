package com.river.ms.business.web;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.river.core.result.JsonResult;
import com.river.ms.business.entity.DataPie;
import com.river.ms.business.entity.ProjectListExt;
import com.river.ms.business.result.ErrorResult;
import com.river.ms.business.service.MPProjectListExtService;

/**
 * <p>
 * 项目信息扩展表（数据） 前端控制器
 * </p>
 *
 * @author zyb
 * @since 2017-12-04
 */
@RestController
@RequestMapping("/projectListExt")
public class ProjectListExtAction {
	
	@Autowired
	private MPProjectListExtService service;

	/**
	 * 项目信息扩展表（数据）添加
	 * 
	 * @param request
	 * @param entity
	 * @return
	 */
	@RequestMapping(value = "/insertProjectListExt", method = RequestMethod.POST)
	public JsonResult insertProjectListExt(HttpServletRequest request,
			@RequestParam(name = "entitys") List<ProjectListExt> entitys,
			@RequestParam(name = "projectId") Long projectId) {
		for (ProjectListExt p : entitys) {
			p.setCreateTime(new Date());
			p.setProjectId(projectId);
		}
		boolean insert = this.service.insertBatch(entitys);
		if (insert) {
			return JsonResult.SUCCESS;
		} else {
			return ErrorResult.INSERT_PROJECT_ERROE;
		}
	}

	/**
	 * 项目信息扩展表（数据）查看
	 * 
	 * @param request
	 * @param entity
	 * @return
	 */
	@RequestMapping(value = "/selectProjectListExt", method = RequestMethod.POST)
	public JsonResult selectProjectListExt(HttpServletRequest request,
			@RequestParam(name = "projectId") Long projectId) {
		Map<String, Object> columnMap = new HashMap<>();
		columnMap.put("projectId", projectId);
		List<ProjectListExt> selectByMap = this.service.selectByMap(columnMap);
		return JsonResult.success(selectByMap);
	}
	/**
	 * 
	 * @param request
	 * @param year
	 * @param type
	 * @param categoryId
	 * @return
	 */
	@RequestMapping(value = "/count", method = RequestMethod.POST)
	public JsonResult count(HttpServletRequest request,
			@RequestParam(name = "year") Integer year,@RequestParam(name = "type") Integer type,@RequestParam(name = "categoryId") String categoryId) {
		DataPie data=this.service.getCountStatistics(year, type, categoryId);
		return JsonResult.success(data);
	}
	/**
	 * 
	 */
	@RequestMapping(value = "/countBy", method = RequestMethod.POST)
	public JsonResult countBy(HttpServletRequest request,
			@RequestParam(name = "year") Integer year,@RequestParam(name = "type") Integer type,@RequestParam(name = "categoryId") String categoryId) {
		List<Map<String,Object>> data=this.service.getCountStatisticsBy(year, type, categoryId);
		return JsonResult.success(data);
	}
	/**
	 * 
	 * @param request
	 * @param itcode
	 * @return
	 */
	@RequestMapping(value = "/count_mytodo", method = RequestMethod.POST)
	public JsonResult count_mytodo(HttpServletRequest request,
			@RequestParam(name = "itcode") String itcode) {
		List<Map<String,Object>> data=this.service.countMytodo(itcode);
		return JsonResult.success(data);
	}
	/**
	 * 
	 * @param request
	 * @param year
	 * @param type
	 * @param categoryId
	 * @return
	 */
	@RequestMapping(value = "/invest", method = RequestMethod.POST)
	public JsonResult invest(HttpServletRequest request,
			@RequestParam(name = "year") Integer year,@RequestParam(name = "type") Integer type,@RequestParam(name = "categoryId") String categoryId) {
		DataPie data=this.service.getInvestStatistics(year, type, categoryId);
		return JsonResult.success(data);
	}
	/**
	 * 获取行业公司
	 * @author zyb
	 * @param request
	 * @param year
	 * @return
	 */
	@RequestMapping(value = "/getCompany", method = RequestMethod.POST)
	public JsonResult getCompany(HttpServletRequest request,
			@RequestParam(name = "year") Integer year) {
		List<Map<String,Object>> data=this.service.getCompany(year);
		return JsonResult.success(data);
	}
	/**
	 * 获取项目类型
	 * @author zyb
	 * @param request
	 * @param year
	 * @param groupCode
	 * @return
	 */
	@RequestMapping(value = "/getProjectType", method = RequestMethod.POST)
	public JsonResult getProjectType(HttpServletRequest request,
			@RequestParam(name = "year") Integer year,@RequestParam(name = "groupCode") String groupCode) {
		List<Map<String,Object>> data=this.service.getProjectType(year,groupCode);
		return JsonResult.success(data);
	}
	/**
	 * 
	 * @param request
	 * @param year
	 * @return
	 */
	@RequestMapping(value = "/computeGroup", method = RequestMethod.POST)
	public JsonResult computeGroup(HttpServletRequest request,
			@RequestParam(name = "year") Integer year) {
		List<Map<String,Object>> data=this.service.computeGroup(year);
		return JsonResult.success(data);
	}

}
