package com.river.ms.business.web;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.river.core.result.JsonResult;
import com.river.ms.business.entity.ProjectHistory;
import com.river.ms.business.result.ErrorResult;
import com.river.ms.business.service.MPProjectHistoryService;

/**
 * <p>
 * 项目预测数据/历史数据 前端控制器
 * </p>
 *
 * @author zyb
 * @since 2017-12-04
 */
@RestController
@RequestMapping("/projectHistory")
public class ProjectHistoryAction {

	@Autowired
	private MPProjectHistoryService service;

	/**
	 * 项目预测数据/历史数据添加
	 * 
	 * @param request
	 * @param entity
	 * @return
	 */
	@RequestMapping(value = "/insertProjectHistory", method = RequestMethod.POST)
	public JsonResult insertProjectHistory(HttpServletRequest request, ProjectHistory projectHistory) {
		projectHistory.setCreateTime(new Date());
		Boolean exit = this.service.isExist(projectHistory);
		if (exit) {
			return ErrorResult.DATA_EXIST;
		} else {
			boolean insert = this.service.insert(projectHistory);
			if (insert) {
				return JsonResult.success(projectHistory);
			} else {
				return ErrorResult.INSERT_PROJECT_ERROE;
			}
		}
	}

	/**
	 * 项目预测数据/历史数据修改
	 * 
	 * @param request
	 * @param entity
	 * @return
	 */
	@RequestMapping(value = "/updateProjectHistory", method = RequestMethod.POST)
	public JsonResult updateProjectHistory(HttpServletRequest request, ProjectHistory projectHistory) {
		projectHistory.setUpdateTime(new Date());
		boolean updateById = this.service.updateById(projectHistory);
		if (updateById) {
			return JsonResult.success(projectHistory);
		} else {
			return ErrorResult.INSERT_PROJECT_ERROE;
		}
	}

	/**
	 * 项目预测数据/历史数据查看
	 * 
	 * @param request
	 * @param entity
	 * @return
	 */
	@RequestMapping(value = "/selectProjectHistory", method = RequestMethod.POST)
	public JsonResult selectProjectHistory(HttpServletRequest request, @RequestParam(name = "projectId") Long projectId,
			@RequestParam(name = "entityType") Integer entityType) {
		/*Map<String, Object> columnMap = new HashMap<>();
		columnMap.put("projectId", projectId);
		columnMap.put("entityType", entityType);
		List<ProjectHistory> selectByMap = this.service.selectByMap(columnMap);*/
		List<ProjectHistory> selectByMap=this.service.getProjectHistory(projectId, entityType);
		return JsonResult.success(selectByMap);
	}

	/**
	 * 项目预测数据/历史数据删除
	 * 
	 * @param request
	 * @param entity
	 * @return
	 */
	@RequestMapping(value = "/deleteProjectHistory", method = RequestMethod.POST)
	public JsonResult deleteProjectHistory(HttpServletRequest request, @RequestParam(name = "entityId") Long entityId) {
		boolean deleteById = this.service.deleteById(entityId);
		return JsonResult.success(deleteById);
	}
}
