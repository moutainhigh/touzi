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
import com.river.ms.business.entity.ProjectMilestoneHistory;
import com.river.ms.business.result.ErrorResult;
import com.river.ms.business.service.MPProjectMilestoneHistoryService;

/**
 * <p>
 * 项目进度更新记录 前端控制器
 * </p>
 *
 * @author zyb
 * @since 2017-12-04
 */
@RestController
@RequestMapping("/projectMilestoneHistory")
public class ProjectMilestoneHistoryAction {

	@Autowired
	MPProjectMilestoneHistoryService service;

	/**
	 * 添加项目成本更新记录
	 * 
	 * @param request
	 * @param entity
	 * @return
	 */
	@RequestMapping(value = "/insertProjectMilestoneHistory", method = RequestMethod.POST)
	public JsonResult insertProjectMilestoneHistory(HttpServletRequest request, ProjectMilestoneHistory entity) {
		entity.setCreateTime(new Date());
		boolean insert = this.service.insert(entity);
		if (insert) {
			return JsonResult.success(entity);
		} else {
			return ErrorResult.INSERT_PROJECT_ERROE;
		}
	}

	/**
	 * 查询项目成本更新记录
	 * 
	 * @param request
	 * @param entity
	 * @return
	 */
	@RequestMapping(value = "/selectProjectMilestoneHistory", method = RequestMethod.POST)
	public JsonResult selectProjectMilestoneHistory(HttpServletRequest request,
			@RequestParam("projectId") Long projectId, @RequestParam("milestoneId") Long milestoneId) {
		Map<String, Object> columnMap = new HashMap<>();
		columnMap.put("projectId", projectId);
		columnMap.put("milestoneId", milestoneId);
		List<ProjectMilestoneHistory> selectByMap = this.service.selectByMap(columnMap);
		return JsonResult.success(selectByMap);
	}
}
