package com.river.ms.project.web;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.river.ms.project.service.MPProjectListService;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.river.core.result.JsonResult;
import com.river.core.utils.SessionUtils;
import com.river.ms.project.entity.ProjectList;
import com.river.ms.project.entity.ProjectStage;
import com.river.ms.project.entity.ProjectStageDoc;
import com.river.ms.project.service.MPProjectStageDocService;
import com.river.ms.project.service.MPProjectStageService;
import com.river.ms.project.service.feign.impl.ToBusinessImpl;

/**
 * <p>
 * 项目阶段文档匹配 前端控制器
 * </p>
 *
 * @author zyb
 * @since 2017-12-04
 */
@RestController
@RequestMapping("/projectStageDoc")
public class ProjectStageDocAction {

	@Autowired
	MPProjectStageDocService service;

	@Autowired
	MPProjectListService projectListService;

	@Autowired
	ToBusinessImpl toBusiness;
	
	@Autowired
	MPProjectStageService projectStageService;

	/**
	 * 获取项目关键文档
	 * 
	 * @return
	 */
	@RequestMapping(value = "/getProjectStageDoc", method = RequestMethod.POST)
	public JsonResult getProjectStageDoc(HttpServletRequest request, @RequestParam(value = "projectId") Long projectId,
			@RequestParam(value = "stageId", required = false) Long stageId,
			@RequestParam(value = "type", required = false) Integer type,
			@RequestParam(value = "flowType", required = false) Integer flowType) {
		if (projectId == null || projectId == 0) {
			return JsonResult.FAILURE;
		}
		ProjectList project = projectListService.selectById(projectId);
		if (project == null || project.getCategoryId2() == null || project.getCategoryId2() == 0) {
			return JsonResult.FAILURE;
		}
		Long categoryId = project.getCategoryId2();
		if (stageId == null || stageId == 0) {
			Map<String, Object> headerMap = SessionUtils.getHeaderMap(request);
			Long flowId = toBusiness.getFlowId(headerMap, categoryId, flowType);
			EntityWrapper<ProjectStage> wrapper = new EntityWrapper<>();
			wrapper.eq("level", 0);
			wrapper.eq("parentId", 0);
			wrapper.eq("flowId", flowId);
			List<ProjectStage> selectList = projectStageService.selectList(wrapper);
			if(selectList != null && selectList.size()>0) {
				stageId = selectList.get(0).getEntityId();
			}
		}
		List<ProjectStageDoc> projectStageDocs = this.service.getProjectStageDocs(stageId, type, categoryId);
		return JsonResult.success(projectStageDocs);
	}
}
