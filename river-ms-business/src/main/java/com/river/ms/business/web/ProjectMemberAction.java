package com.river.ms.business.web;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.river.core.entity.UserEntity;
import com.river.core.result.JsonResult;
import com.river.core.utils.SessionUtils;
import com.river.ms.business.entity.ProjectMember;
import com.river.ms.business.feign.serviceImpl.ToProjectImpl;
import com.river.ms.business.result.ErrorResult;
import com.river.ms.business.service.MPProjectMemberService;
import com.river.ms.business.service.MPProjectTodoService;

/**
 * <p>
 * 项目组成员 前端控制器
 * </p>
 *
 * @author zyb
 * @since 2017-12-04
 */
@RestController
@RequestMapping("/projectMember")
public class ProjectMemberAction {

	@Autowired
	MPProjectMemberService service;

	@Autowired
	ToProjectImpl toProject;

	@Autowired
	MPProjectTodoService projectTodoService;

	/**
	 * 项目组成员选择
	 * 
	 * @param request
	 * @param entitys
	 * @return
	 */
	@RequestMapping(value = "/insertProjectMember", method = RequestMethod.POST)
	public JsonResult insertProjectMember(HttpServletRequest request, @RequestParam(name = "str") String str) {
		List<ProjectMember> entitys = JSONArray.parseArray(str, ProjectMember.class);
		if (entitys != null && entitys.size() > 0) {
			entitys = this.service.isExit(entitys);
			UserEntity user = SessionUtils.getUser(request);
			for (ProjectMember p : entitys) {
				p.setItcode(user.getEntityCode());
				p.setResourceId(user.getResId());
				p.setState(0);
				p.setCreateTime(new Date());
			}
			if (entitys != null && entitys.size() > 0) {
				Boolean insert = this.service.insertBatch(entitys);
				if (insert) {
					return JsonResult.success(entitys);
				} else {
					return ErrorResult.INSERT_PROJECT_ERROE;
				}
			} else {
				return ErrorResult.INSERT_PROJECT_ERROE;
			}
		} else {
			return JsonResult.success(entitys);
		}

	}

	@RequestMapping(value = "/updateProjectMemberType", method = RequestMethod.POST)
	public JsonResult updateProjectMemberType(HttpServletRequest request,
			@RequestParam(name = "memberType") Integer memberType, @RequestParam(name = "projectId") Long projectId,
			@RequestParam(name = "memberId") Long memberId) {
		ProjectMember entity = new ProjectMember();
		entity.setMemberType(memberType);
		EntityWrapper<ProjectMember> wrapper = new EntityWrapper<>();
		wrapper.eq("projectId", projectId);
		wrapper.eq("memberId", memberId);
		boolean update = this.service.update(entity, wrapper);
		if (update) {
			return JsonResult.SUCCESS;
		} else {
			return JsonResult.FAILURE;
		}
	}

	/**
	 * 项目组成员确认
	 * 
	 * @param request
	 * @param entitys
	 * @return
	 */
	@RequestMapping(value = "/projectMemberNotarize", method = RequestMethod.POST)
	public JsonResult projectMemberNotarize(HttpServletRequest request,
			@RequestParam(name = "entitys") List<Long> ids) {
		List<ProjectMember> selectBatchIds = this.service.selectBatchIds(ids);
		for (ProjectMember p : selectBatchIds) {
			p.setState(1);
		}
		Boolean insert = this.service.updateBatchById(selectBatchIds);
		if (insert) {
			return JsonResult.SUCCESS;
		} else {
			return ErrorResult.INSERT_PROJECT_ERROE;
		}
	}

	/**
	 * 删除项目组成员
	 * 
	 * @param request
	 * @param entitys
	 * @return
	 */
	@RequestMapping(value = "/deleteProjectMember", method = RequestMethod.POST)
	public JsonResult deleteProjectMember(HttpServletRequest request, @RequestParam(name = "projectId") Long projectId,
			@RequestParam(name = "memberId") Long memberId) {
		EntityWrapper<ProjectMember> wrapper = new EntityWrapper<>();
		wrapper.eq("projectId", projectId);
		wrapper.eq("memberId", memberId);
		boolean delete = this.service.delete(wrapper);
		if (delete) {
			return JsonResult.SUCCESS;
		} else {
			return ErrorResult.INSERT_PROJECT_ERROE;
		}
	}

	/**
	 * 项目组成员离开
	 * 
	 * @param request
	 * @param entitys
	 * @return
	 */
	@RequestMapping(value = "/projectMemberDelete", method = RequestMethod.POST)
	public JsonResult projectMemberDelete(HttpServletRequest request, @RequestParam(name = "memberId") Long id) {
		ProjectMember selectBatchIds = this.service.selectById(id);
		selectBatchIds.setState(2);
		Boolean insert = this.service.updateById(selectBatchIds);
		if (insert) {
			return JsonResult.SUCCESS;
		} else {
			return ErrorResult.INSERT_PROJECT_ERROE;
		}
	}

	/**
	 * 获取项目组成员
	 * 
	 * @param projectId
	 * @param stage
	 * @return
	 */
	@RequestMapping(value = "/getProjectMember", method = RequestMethod.POST)
	public JsonResult getProjectMember(@RequestParam(name = "projectId") Long projectId) {
		Map<String, Object> columnMap = new HashMap<>();
		columnMap.put("projectId", projectId);
		List<ProjectMember> selectByMap = this.service.selectByMap(columnMap);
		return JsonResult.success(selectByMap);
	}

	/**
	 * 根据用户itcode统计参与过的项目
	 * 
	 * @param request
	 * @param memberitcode
	 * @return
	 */
	@RequestMapping(value = "/countProjectNumByResId", method = RequestMethod.POST)
	public JsonResult countProjectNumByResId(HttpServletRequest request,
			@RequestParam(name = "itCode", required = false) String itCode,
			@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
		if (itCode == null) {
			UserEntity user = SessionUtils.getUser(request);
			itCode = user.getEntityCode();
		}
		PageHelper.startPage(page, pageSize);
		Map<String, Object> columnMap = new HashMap<>();
		columnMap.put("memberitcode", itCode);
		List<ProjectMember> selectByMap = this.service.selectByMap(columnMap);
		PageInfo<ProjectMember> pageInfo = new PageInfo<ProjectMember>(selectByMap, 5);
		return JsonResult.successPage(selectByMap, pageInfo);
	}
}
