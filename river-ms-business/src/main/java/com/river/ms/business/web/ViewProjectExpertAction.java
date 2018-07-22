package com.river.ms.business.web;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.river.core.result.JsonResult;
import com.river.core.utils.SessionUtils;
import com.river.ms.business.entity.ViewProjectExpert;
import com.river.ms.business.feign.serviceImpl.ToProjectImpl;
import com.river.ms.business.service.MPViewProjectExpertService;

/**
 * <p>
 * VIEW 前端控制器
 * </p>
 *
 * @author zyb
 * @since 2017-12-29
 */
@RestController
@RequestMapping("/viewProjectExpert")
public class ViewProjectExpertAction {

	@Autowired
	MPViewProjectExpertService service;

	@Autowired
	ToProjectImpl toProject;

	@RequestMapping(value = "/selectViewProjectExpertByProjectId", method = RequestMethod.POST)
	public JsonResult selectViewProjectExpertByProjectId(HttpServletRequest request,
			@RequestParam(name = "projectId") Long projectId) {
		List<ViewProjectExpert> selectViewProjectExpertByProjectId = this.service
				.selectViewProjectExpertByProjectId(projectId);
		return JsonResult.success(selectViewProjectExpertByProjectId);
	}

	@RequestMapping(value = "/selectAllViewProjectExpertByProjectId", method = RequestMethod.POST)
	public JsonResult selectAllViewProjectExpertByProjectId(HttpServletRequest request,
			@RequestParam(name = "projectId") Long projectId,
			@RequestParam(name = "stage", required = false) Long stage,
			@RequestParam(name = "processInstanceId", required = false) String processInstanceId,
			@RequestParam(name = "isMerge", required = false, defaultValue = "true") Boolean isMerge) {
		if (stage != null && stage != 0) {
			Map<String, Object> headerMap = SessionUtils.getHeaderMap(request);
			Map<Long, Long> greatStage = toProject.getProjectStageNode(headerMap, stage);
			Set<Entry<Long, Long>> entrySet = greatStage.entrySet();
			for (Entry<Long, Long> entry : entrySet) {
				stage = entry.getKey();
			}
		}
		List<ViewProjectExpert> selectViewProjectExpertByProjectId = this.service
				.selectAllViewProjectExpertByProjectId(request,projectId, stage, processInstanceId,isMerge);
		return JsonResult.success(selectViewProjectExpertByProjectId);
	}

}
