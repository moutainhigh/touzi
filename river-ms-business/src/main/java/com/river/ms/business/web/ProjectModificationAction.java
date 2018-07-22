package com.river.ms.business.web;

import java.util.Date;
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
import com.river.ms.business.entity.ProjectModification;
import com.river.ms.business.result.ErrorResult;
import com.river.ms.business.service.MPProjectModificationService;

/**
 * <p>
 * 项目变更 前端控制器
 * </p>
 *
 * @author zyb
 * @since 2017-12-04
 */
@RestController
@RequestMapping("/projectModification")
public class ProjectModificationAction {

	@Autowired
	MPProjectModificationService service;

	/**
	 * 添加项目变更
	 * 
	 * @param request
	 * @param entity
	 * @return
	 */
	@RequestMapping(value = "/insertProjectModification", method = RequestMethod.POST)
	public JsonResult insertProjectModification(HttpServletRequest request, ProjectModification entity,
			@RequestParam(name = "str", required = false) String str,
			@RequestParam(name = "docStr", required = false) String docStr) {
		UserEntity user = SessionUtils.getUser(request);
		entity.setItcode(user.getEntityCode());
		entity.setResourceId(user.getResId());
		entity.setState(0);
		entity.setCreateTime(new Date());
		entity.setOnline(0);
		Map<String, Object> headerMap = SessionUtils.getHeaderMap(request);
		Boolean insertProjectModification = this.service.insertProjectModification(entity, headerMap, str,docStr,user);
		if (insertProjectModification) {
			return JsonResult.success(entity);
		}
		return ErrorResult.UPDATE_ERROR;
	}

	/**
	 * 打回变更料上传
	 * 
	 * @param request
	 * @param entity
	 * @return
	 */
	@RequestMapping(value = "/updateProjectModification", method = RequestMethod.POST)
	public JsonResult updateProjectModification(HttpServletRequest request, ProjectModification entity,
			@RequestParam(name = "docStr", required = false) String docStr) {
		Boolean updateProjectModification = this.service.updateProjectModification(request, entity,docStr);
		if (updateProjectModification) {
			return JsonResult.success(entity);
		}
		return ErrorResult.UPDATE_ERROR;
	}

	/**
	 * 获取项目变更信息
	 * 
	 * @param request
	 * @param projectId
	 * @return
	 */
	@RequestMapping(value = "/getProjectModificationByProjectId", method = RequestMethod.POST)
	public JsonResult getProjectModificationByProjectId(HttpServletRequest request,
			@RequestParam("projectId") Long projectId,
			@RequestParam(name = "processInstanceId", required = false) String processInstanceId) {
		List<ProjectModification> projectModificationByProjectId = this.service
				.getProjectModificationByProjectId(projectId,processInstanceId);
		return JsonResult.success(projectModificationByProjectId);
	}

}
