package com.river.ms.business.web;


import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.river.core.result.JsonResult;
import com.river.ms.business.entity.ProjectFlowVariable;
import com.river.ms.business.service.MPProjectFlowVariableService;
import com.river.ms.business.utils.ActivitiParameterHandle;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zyb
 * @since 2018-01-29
 */
@RestController
@RequestMapping("/projectFlowVariable")
public class ProjectFlowVariableAction {
	
	@Autowired
	MPProjectFlowVariableService projectFlowVariableService;
	
	@Autowired
	ActivitiParameterHandle activitiParameterHandle;

	@RequestMapping(value = "/select", method = RequestMethod.POST)
	public JsonResult select(HttpServletRequest request) {
		List<ProjectFlowVariable> projectFlowVariable = projectFlowVariableService.getProjectFlowVariable(null, null, null);
		Map<String, Object> getactivitiQueryMap = activitiParameterHandle.getactivitiQueryMap(request, 1l, projectFlowVariable);
		return JsonResult.success(getactivitiQueryMap);
	}
}

