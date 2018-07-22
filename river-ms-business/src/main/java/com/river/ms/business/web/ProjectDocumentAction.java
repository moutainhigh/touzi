package com.river.ms.business.web;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONArray;
import com.river.core.entity.UserEntity;
import com.river.core.result.JsonResult;
import com.river.core.utils.SessionUtils;
import com.river.ms.business.entity.ProjectDocument;
import com.river.ms.business.feign.serviceImpl.ToProjectImpl;
import com.river.ms.business.result.ErrorResult;
import com.river.ms.business.service.MPProjectDocumentService;

/**
 * <p>
 * 项目文档 前端控制器
 * </p>
 *
 * @author zyb
 * @since 2017-12-04
 */
@RestController
@RequestMapping("/projectDocument")
public class ProjectDocumentAction {

	@Autowired
	MPProjectDocumentService service;

	@Autowired
	ToProjectImpl toProject;

	/**
	 * 添加项目文档
	 * 
	 * @param request
	 * @param entity
	 * @return
	 */
	@RequestMapping(value = "/insertProjectDocument", method = RequestMethod.POST)
	public JsonResult insertProjectDocument(HttpServletRequest request, @RequestParam(name = "str") String str) {
		UserEntity user = SessionUtils.getUser(request);
		List<ProjectDocument> entitys = JSONArray.parseArray(str, ProjectDocument.class);

		Map<String, Object> headerMap = SessionUtils.getHeaderMap(request);
		if (entitys != null && entitys.size() > 0) {
			Long projectId = entitys.get(0).getProjectId();
			Long stageId = toProject.getStageIdByProjectId(headerMap, projectId);
			String projectProcessInstanceId = toProject.getProjectProcessInstanceId(headerMap, projectId);
			Long greatStageId = new Long(0);
			if (stageId != null && stageId != 0) {
				Map<Long, Long> greatStage = toProject.getProjectStageNode(headerMap, stageId);
				Set<Entry<Long, Long>> entrySet = greatStage.entrySet();
				for (Entry<Long, Long> entry : entrySet) {
					greatStageId = entry.getKey();
				}
			}
			for (ProjectDocument entity : entitys) {
				entity.setItcode(user.getEntityCode());
				entity.setResourceId(user.getResId());
				entity.setResName(user.getEntityTitle());
				entity.setCreateTime(new Date());
				entity.setGreatStage(greatStageId);
				entity.setStage(stageId);
				entity.setPROCESS_INSTANCE_ID_(projectProcessInstanceId);
			}
			boolean insert = this.service.insertBatch(entitys);
			if (insert) {
				return JsonResult.SUCCESS;
			} else {
				return ErrorResult.INSERT_PROJECT_ERROE;
			}
		} else {
			return JsonResult.SUCCESS;
		}
	}

	/**
	 * 获取项目文档
	 * 
	 * @param projectId
	 * @param stage
	 * @return
	 */
	@RequestMapping(value = "/getProjectDocuments", method = RequestMethod.POST)
	public JsonResult getProjectDocuments(HttpServletRequest request, @RequestParam(name = "projectId") Long projectId,
			@RequestParam(name = "stage", required = false) Long stage,
			@RequestParam(name = "greatStage", required = false) Long greatStage,
			@RequestParam(name = "type", required = false) Long type,
			@RequestParam(name = "processInstanceId", required = false) String processInstanceId,
			@RequestParam(name = "isAll", required = false, defaultValue = "true") Boolean isAll) {
		List<ProjectDocument> selectByMap = this.service.getByProjectId(projectId, stage, greatStage, type,
				processInstanceId,isAll);
		return JsonResult.success(selectByMap);
	}
}
