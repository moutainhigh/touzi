package com.river.ms.res.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.river.core.entity.UserEntity;
import com.river.core.result.JsonResult;
import com.river.core.utils.SessionUtils;
import com.river.ms.res.entity.ProjectFocuson;
import com.river.ms.res.result.ErrorResult;
import com.river.ms.res.service.MPProjectFocusonService;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author zyb
 * @since 2017-12-27
 */
@RestController
@RequestMapping("/projectFocuson")
public class ProjectFocusonAction {
	
	/**
	 * 日志
	 */
	private final Logger logger = Logger.getLogger(getClass());

	@Autowired
	MPProjectFocusonService projectFocusonService;

	/**
	 * 关注项目批量添加
	 * 
	 * @return
	 */
	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public JsonResult insertProjectFocuson(HttpServletRequest request, ProjectFocuson projectEntity) {
		UserEntity user = SessionUtils.getUser(request);
		if (projectEntity != null) {
			projectEntity.setResourceId(user.getResId());
		} else {
			logger.debug("参数错误");
			return ErrorResult.PARAMETER_ERROR;
		}
		try {
			this.projectFocusonService.insert(projectEntity);
		} catch (Exception e) {
			logger.debug("关注项目插入失败");
			return ErrorResult.INSERT_PROJECT_FOCUSON;
		}
		logger.debug("关注项目插入成功");
		return JsonResult.success(projectEntity);
	}

	/**
	 * 关注项目，取消关注项目
	 * 
	 * @return
	 */
	@RequestMapping(value = "/insertDeleteProjectFocuson", method = RequestMethod.POST)
	public JsonResult insertDeleteProjectFocuson(HttpServletRequest request,
			@RequestParam(name = "resourceId", required = false) Long resourceId,
			@RequestParam(name = "projectId") Long projectId,
			@RequestParam(name = "projectTitle") String projectTitle) {
		if (resourceId == null) {
			UserEntity user = SessionUtils.getUser(request);
			resourceId = user.getResId();
		}
		EntityWrapper<ProjectFocuson> wrapper = new EntityWrapper<>();
		wrapper.eq("projectId", projectId);
		wrapper.eq("resourceId", resourceId);
		List<ProjectFocuson> selectList = this.projectFocusonService.selectList(wrapper);
		if (selectList != null && selectList.size() > 0) {
			for (ProjectFocuson projectFocuson : selectList) {
				this.projectFocusonService.deleteById(projectFocuson.getEntityId());
			}
			return JsonResult.success("项目取消关注成功！");
		} else {
			ProjectFocuson projectFocuson = new ProjectFocuson();
			projectFocuson.setProjectId(projectId);
			projectFocuson.setProjectTitle(projectTitle);
			projectFocuson.setResourceId(resourceId);
			this.projectFocusonService.insert(projectFocuson);
			return JsonResult.success("项目关注成功！");
		}
	}

	/**
	 * 取消关注项目
	 * 
	 * @return
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public JsonResult deleteProjectFocuson(HttpServletRequest request, @RequestParam("projectId") Long projectId) {
		UserEntity user = SessionUtils.getUser(request);
		try {
			Map<String, Object> columnMap = new HashMap<>();
			columnMap.put("projectId", projectId);
			columnMap.put("resourceId", user.getResId());
			this.projectFocusonService.deleteByMap(columnMap);
		} catch (Exception e) {
			logger.debug("取消关注项目失败");
			return ErrorResult.DELETE_PROJECT_FOCUSON_ERROR;
		}
		logger.debug("取消关注项目成功");
		return JsonResult.SUCCESS;
	}

	/**
	 * 关注项目列表查找
	 * 
	 * @return
	 */
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public JsonResult selectProjectFocusonList(HttpServletRequest request) {
		UserEntity user = SessionUtils.getUser(request);
		List<ProjectFocuson> pageProjectFocusonByUser = null;
		try {
			Map<String, Object> columnMap = new HashMap<>();
			columnMap.put("resourceId", user.getResId());
			pageProjectFocusonByUser = this.projectFocusonService.selectByMap(columnMap);
		} catch (Exception e) {
			logger.debug("查找关注项目列表失败");
			return ErrorResult.QUERY_PROJECT_FOCUSON_ERROR;
		}
		logger.debug("查找关注项目列表成功");
		return JsonResult.success(pageProjectFocusonByUser);
	}

	/**
	 * 关注项目列表查找
	 * 
	 * @return
	 */
	@RequestMapping(value = "/selectProjectIdsFocusonList", method = RequestMethod.POST)
	public Map<Long, Long> selectProjectIdsFocusonList(HttpServletRequest request) {
		UserEntity user = SessionUtils.getUser(request);
		Map<Long, Long> result = new HashMap<>();
		List<ProjectFocuson> pageProjectFocusonByUser = null;
		Map<String, Object> columnMap = new HashMap<>();
		columnMap.put("resourceId", user.getResId());
		pageProjectFocusonByUser = this.projectFocusonService.selectByMap(columnMap);
		for (ProjectFocuson f : pageProjectFocusonByUser) {
			result.put(f.getProjectId(), f.getProjectId());
		}
		return result;
	}

	/**
	 * 获取用户对某个项目的关注信息
	 * 
	 * @param request
	 * @param resourceId
	 * @param projectId
	 * @return
	 */
	@RequestMapping(value = "/getProjectVerifyMessage", method = RequestMethod.POST)
	public JsonResult getProjectVerifyMessage(HttpServletRequest request,
			@RequestParam(name = "resourceId", required = false) Long resourceId,
			@RequestParam(name = "projectId") Long projectId) {
		if (resourceId == null) {
			UserEntity user = SessionUtils.getUser(request);
			if (user != null) {
				resourceId = user.getResId();
			}else {
				return JsonResult.FAILURE;
			}
		}
		EntityWrapper<ProjectFocuson> wrapper = new EntityWrapper<>();
		wrapper.eq("projectId", projectId);
		wrapper.eq("resourceId", resourceId);
		List<ProjectFocuson> selectList = this.projectFocusonService.selectList(wrapper);
		if (selectList != null && selectList.size() > 0) {
			return JsonResult.success(true);
		} else {
			return JsonResult.success(false);
		}
	}
}
