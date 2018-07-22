package com.river.ms.business.web;


import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.river.core.entity.RoleEntity;
import com.river.core.entity.UserEntity;
import com.river.core.result.JsonResult;
import com.river.core.utils.SessionUtils;
import com.river.ms.business.entity.ProjectTodo;
import com.river.ms.business.service.MPProjectTodoService;

/**
 * <p>
 * 待办事项：每次处理完更新此表 前端控制器
 * </p>
 *
 * @author zyb
 * @since 2017-12-13
 */
@RestController
@RequestMapping("/projectTodo")
public class ProjectTodoAction {

	@Autowired
	MPProjectTodoService service;
	
	/**
	 * 筛选用户所能看到的项目ID
	 * 
	 * @param request
	 * @param entity
	 * @return
	 */
	@RequestMapping(value = "/getProjectToDo", method = RequestMethod.POST)
	public JsonResult getProjectToDo(HttpServletRequest request,@RequestParam("roleId") Long roleId) {
		UserEntity user = SessionUtils.getUser(request);
		List<RoleEntity> roles = user.getRoles();
		List<ProjectTodo> projectList = new ArrayList<>();
		for(RoleEntity r : roles) {
			if(r.getEntityId().equals(roleId)) {
				Long resId = user.getResId();
				projectList = this.service.getProjectList(r, resId);
				break;
			}
		}
		List<Long> projectIds = new ArrayList<>();
		for(ProjectTodo p : projectList) {
			projectIds.add(p.getProjectId());
		}
		return JsonResult.success(projectIds);
	}
	/**
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/getMyProjectToDo", method = RequestMethod.POST)
	public JsonResult getMyProjectToDo(HttpServletRequest request) {
		UserEntity user = SessionUtils.getUser(request);
		List<RoleEntity> roles = user.getRoles();
		Long resId = user.getResId();
		List<ProjectTodo> todoList=this.service.getProjectTodos(roles, resId);
		return JsonResult.success(todoList);
	}
	
	@RequestMapping(value = "/insertProjectToDo", method = RequestMethod.POST)
	public JsonResult insertProjectToDo(HttpServletRequest request,ProjectTodo entity) {
		boolean insert = this.service.insert(entity);
		return JsonResult.success(entity);
	}
}

