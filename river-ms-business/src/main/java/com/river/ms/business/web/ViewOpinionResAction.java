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

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.river.core.result.JsonResult;
import com.river.core.utils.SessionUtils;
import com.river.ms.business.entity.ViewOpinionRes;
import com.river.ms.business.feign.serviceImpl.ToProjectImpl;
import com.river.ms.business.service.impl.ViewOpinionResServiceDiy;

/**
 * <p>
 * VIEW 前端控制器
 * </p>
 *
 * @author zyb
 * @since 2017-12-19
 */
@RestController
@RequestMapping("/viewOpinionRes")
public class ViewOpinionResAction {

	@Autowired
	ViewOpinionResServiceDiy service;

	@Autowired
	ToProjectImpl toProject;

	@RequestMapping(value = "/getProjectOpinion", method = RequestMethod.POST)
	public JsonResult getProjectOpinion(HttpServletRequest request, @RequestParam(value = "projectId") Long projectId,
			@RequestParam(value = "stage",required=false) Long stage) {
		Map<String, Object> headerMap = SessionUtils.getHeaderMap(request);
		// 获取项目阶段ID
		if (stage == null) {
			stage = this.toProject.getStageIdByProjectId(headerMap, projectId);
		}
		// 获取项目大阶段ID
		Map<Long, Long> projectStageNode = this.toProject.getProjectStageNode(headerMap, stage);
		Set<Entry<Long, Long>> entrySet = projectStageNode.entrySet();
		for (Entry<Long, Long> entry : entrySet) {
			stage = entry.getKey();
		}
		// 获取项目所有小阶段IDs
		JsonResult childNodeIds = this.toProject.getAllChildNodeById(headerMap, stage);
		List<Long> childNodeIdList = (List<Long>) childNodeIds.getData();

		EntityWrapper<ViewOpinionRes> viewOpinionResWrapper = new EntityWrapper<>();
		viewOpinionResWrapper.in("stage", childNodeIdList);
		viewOpinionResWrapper.eq("projectId", projectId);
		List<ViewOpinionRes> selectList = this.service.selectList(viewOpinionResWrapper);
		return JsonResult.success(selectList);
	}
}
