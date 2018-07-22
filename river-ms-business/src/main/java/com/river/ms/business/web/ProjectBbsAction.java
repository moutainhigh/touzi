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

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.river.core.entity.UserEntity;
import com.river.core.result.JsonResult;
import com.river.core.utils.SessionUtils;
import com.river.ms.business.entity.ProjectBbs;
import com.river.ms.business.result.ErrorResult;
import com.river.ms.business.service.MPProjectBbsService;

/**
 * <p>
 * 项目讨论区 前端控制器
 * </p>
 *
 * @author zyb
 * @since 2017-12-04
 */
@RestController
@RequestMapping("/projectBbs")
public class ProjectBbsAction {

	@Autowired
	MPProjectBbsService service;

	/**
	 * 添加问答
	 * 
	 * @param request
	 * @param entity
	 * @return
	 */
	@RequestMapping(value = "/insertProjectBbs", method = RequestMethod.POST)
	public JsonResult insertProjectBbs(HttpServletRequest request, @RequestParam(name = "projectId") Long projectId,
			@RequestParam(name = "stage") Long stage, @RequestParam(name = "content") String content,
			@RequestParam(name = "title") String title) {
		if (stage == null || stage == 0) {
			return JsonResult.failure("数据异常！");
		}
		ProjectBbs entity = new ProjectBbs();
		entity.setIsReply(0);
		entity.setProjectId(projectId);
		entity.setStage(stage);
		Map<String, Object> headerMap = SessionUtils.getHeaderMap(request);

		entity.setCreateTime(new Date());
		UserEntity user = SessionUtils.getUser(request);
		if (user == null)
			return JsonResult.failure("user is null");
		entity.setItcode(user.getEntityCode());
		entity.setResourceId(user.getResId());
		boolean insert = this.service.insertBbs(headerMap, entity, content, title, user);
		if (insert) {
			return JsonResult.success(entity);
		} else {
			return ErrorResult.INSERT_PROJECT_ERROE;
		}
	}

	/**
	 * 查询项目问答
	 * 
	 * @param request
	 * @param entity
	 * @return
	 */
	@RequestMapping(value = "/queryProjectBbs", method = RequestMethod.POST)
	public JsonResult queryProjectBbs(HttpServletRequest request, @RequestParam(name = "projectId") Long projectId,
			@RequestParam(name = "stage", required = false) Long stage,
			@RequestParam(name = "isAll", required = false, defaultValue = "false") Boolean isAll) {
		Map<String, Object> headerMap = SessionUtils.getHeaderMap(request);
		List<ProjectBbs> selectByMap = this.service.selectByProjectIdAndStageId(headerMap, projectId, stage, isAll);
		return JsonResult.success(selectByMap);
	}

	/**
	 * 回复问答
	 * 
	 * @param request
	 * @param entityId
	 * @return
	 */
	@RequestMapping(value = "/reply", method = RequestMethod.POST)
	public JsonResult reply(HttpServletRequest request, @RequestParam(name = "questionId") Long entityId) {
		ProjectBbs selectById = this.service.selectById(entityId);
		selectById.setIsReply(1);
		boolean updateById = this.service.updateById(selectById);
		return JsonResult.success(updateById);
	}

	/**
	 * 获取未回复的问答
	 * 
	 * @param request
	 * @param itcode
	 * @return
	 */
	@RequestMapping(value = "/noreply", method = RequestMethod.POST)
	public JsonResult reply(HttpServletRequest request, @RequestParam(name = "itcode") String itcode,
			@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
		PageHelper.startPage(page, pageSize);
		List<ProjectBbs> list = this.service.getNoReply(itcode);
		PageInfo<ProjectBbs> pageInfo = new PageInfo<ProjectBbs>(list, 5);
		return JsonResult.successPage(list,pageInfo);
	}

	/**
	 * 获取已回答的问题列表
	 * 
	 * @param request
	 * @param itcode
	 * @return
	 */
	@RequestMapping(value = "/replied", method = RequestMethod.POST)
	public JsonResult replied(HttpServletRequest request, @RequestParam(name = "itcode") String itcode,
			@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
		PageHelper.startPage(page, pageSize);
		List<ProjectBbs> list = this.service.getReplied(itcode);
		PageInfo<ProjectBbs> pageInfo = new PageInfo<ProjectBbs>(list, 5);
		return JsonResult.successPage(list,pageInfo);
	}
}
