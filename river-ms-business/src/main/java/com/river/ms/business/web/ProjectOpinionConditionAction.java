package com.river.ms.business.web;


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
import com.river.ms.business.entity.ProjectOpinionCondition;
import com.river.ms.business.service.MPProjectOpinionConditionService;

/**
 * <p>
 * 有条件通过时的条件清单 前端控制器
 * </p>
 *
 * @author zyb
 * @since 2017-12-04
 */
@RestController
@RequestMapping("/projectOpinionCondition")
public class ProjectOpinionConditionAction {
	
	@Autowired
	MPProjectOpinionConditionService service;
	/**
	 * 有条件通过时的条件清单列表
	 * @param request
	 * @param projectId
	 * @return
	 */
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public JsonResult getProjectOpinionConditionList(HttpServletRequest request,
			@RequestParam(name = "projectId") Long projectId,
			@RequestParam(name = "type",required=false,defaultValue="0") Integer type) {

		List<ProjectOpinionCondition> list=this.service.getOpinionConditionByProjectId(projectId, type);
		return JsonResult.success(list);
/*		Map<String,Object> temp = new HashMap<>();
		temp.put("type", type);
		temp.put("projectId", projectId);
		List<ProjectOpinionCondition> selectByMap = this.service.selectByMap(temp);
		return JsonResult.success(selectByMap);*/
	}

}

