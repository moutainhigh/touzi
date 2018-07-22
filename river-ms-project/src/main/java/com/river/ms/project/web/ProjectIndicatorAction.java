package com.river.ms.project.web;


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
import com.river.ms.project.entity.ProjectIndicator;
import com.river.ms.project.service.MPProjectIndicatorService;

/**
 * <p>
 * 可配置参数 前端控制器
 * </p>
 *
 * @author zyb
 * @since 2017-12-04
 */
@RestController
@RequestMapping("/projectIndicator")
public class ProjectIndicatorAction {

	@Autowired
	MPProjectIndicatorService service;

	/**
	 * 获取指定参数
	 * @param request
	 * @param entityType
	 * @return
	 */
	@RequestMapping(value = "/getProjectIndicator", method = RequestMethod.POST)
	public JsonResult getProjectIndicator(HttpServletRequest request, @RequestParam(value="entityType") Integer entityType) {
		Map<String, Object> columnMap = new HashMap<>();
		columnMap.put("entityType", entityType);
		List<ProjectIndicator> selectByMap = this.service.selectByMap(columnMap);
		return JsonResult.success(selectByMap);
	}
}

