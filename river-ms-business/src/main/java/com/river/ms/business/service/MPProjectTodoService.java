package com.river.ms.business.service;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.service.IService;
import com.river.core.entity.RoleEntity;
import com.river.ms.business.entity.ProjectTodo;

/**
 * <p>
 * 待办事项：每次处理完更新此表 服务类
 * </p>
 *
 * @author zyb
 * @since 2017-12-13
 */
public interface MPProjectTodoService extends IService<ProjectTodo> {

	/**
	 * 根据角色和用户resId获取项目ID
	 * 
	 * @param roles
	 * @param resId
	 * @return
	 */
	List<ProjectTodo> getProjectList(RoleEntity roles, Long resId);

	List<ProjectTodo> getProjectTodos(List<RoleEntity> roles, Long resId);

	Boolean isExit(Long projectId, Long roleId, Long resourceId, String groupCode, Integer type);

	/**
	 * 添加待办事项
	 * 
	 * @param projectId
	 * @param roleId
	 * @param resourceId
	 * @param itcode
	 * @return
	 */
	boolean insertTodo(Long projectId, Long roleId, Long resourceId, String itcode, Integer type);

	/**
	 * 
	 * @param todo
	 * @return
	 */
	boolean insertTodo(ProjectTodo todo);

	/**
	 * 删除待办事项
	 * 
	 * @param projectId
	 * @param roleId
	 * @param resourceId
	 * @param itcode
	 * @return
	 */
	boolean deleteTodo(Long projectId, Long roleId, Long resourceId, String itcode, Integer type);

	/**
	 * 插入待办事项（专家除外）
	 * 
	 * @param projectId
	 * @param taskCode
	 * @return
	 */
	Map<String, Object> insertTodos(Long projectId, String taskCode);
}
