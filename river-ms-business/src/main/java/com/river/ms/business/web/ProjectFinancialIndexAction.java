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

import com.river.core.entity.UserEntity;
import com.river.core.result.JsonResult;
import com.river.core.utils.SessionUtils;
import com.river.ms.business.entity.ProjectFinancialIndex;
import com.river.ms.business.result.ErrorResult;
import com.river.ms.business.service.MPProjectFinancialIndexService;

/**
 * <p>
 * 项目财务指标 前端控制器
 * </p>
 *
 * @author zyb
 * @since 2017-12-04
 */
@RestController
@RequestMapping("/projectFinancialIndex")
public class ProjectFinancialIndexAction {

	@Autowired
	MPProjectFinancialIndexService service;

	/**
	 * 添加项目财务指标
	 * 
	 * @param request
	 * @param entity
	 * @return
	 */
	@RequestMapping(value = "/insertProjectFinancialIndex", method = RequestMethod.POST)
	public JsonResult insertProjectFinancialIndex(HttpServletRequest request, ProjectFinancialIndex entity) {
		UserEntity user = SessionUtils.getUser(request);
		entity.setItcode(user.getEntityCode());
		entity.setResourceId(user.getResId());
		entity.setCreateTime(new Date());
		boolean insert = this.service.insert(entity);
		if (insert) {
			return JsonResult.success(entity);
		} else {
			return ErrorResult.INSERT_PROJECT_ERROE;
		}
	}

	/**
	 * 查看项目财务指标
	 * 
	 * @param request
	 * @param entity
	 * @return
	 */
	@RequestMapping(value = "/getProjectFinancialIndex", method = RequestMethod.POST)
	public JsonResult getProjectFinancialIndex(@RequestParam("projectId") Long projectId) {
		Map<String, Object> columnMap = new HashMap<>();
		columnMap.put("projectId", projectId);
		List<ProjectFinancialIndex> selectByMap = this.service.selectByMap(columnMap);
		return JsonResult.success(selectByMap);
	}
}
