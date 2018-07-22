package com.river.ms.business.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.river.core.entity.RoleEntity;
import com.river.ms.business.entity.ProjectTodo;
import com.river.ms.business.mapper.ProjectTodoDao;
import com.river.ms.business.service.MPProjectTodoService;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 待办事项：每次处理完更新此表 服务实现类
 * </p>
 *
 * @author zyb
 * @since 2017-12-13
 */
@Service
//@Transactional
public class ProjectTodoServiceDiy extends ServiceImpl<ProjectTodoDao, ProjectTodo> implements MPProjectTodoService {

	@Override
	public List<ProjectTodo> getProjectList(RoleEntity role, Long resId) {
		List<ProjectTodo> result = new ArrayList<>();
		String roleCode = role.getEntityCode();
		Map<String, Object> queryMap = new HashMap<>();
		if (roleCode.equals("ZHUXI")) {
			queryMap.put("roleId", role.getEntityId());
		}
		if (roleCode.equals("ZHUANJIA")) {
			queryMap.put("roleId", role.getEntityId());
			queryMap.put("resourceId", resId);
		}
		if (roleCode.equals("YIBASHOU")) {
			queryMap.put("roleId", role.getEntityId());
			/* queryMap.put("resourceId", resId); */
		}
		if (roleCode.equals("YEWURENYUAN")) {
			queryMap.put("roleId", role.getEntityId());
			queryMap.put("resourceId", resId);
		}
		if (roleCode.equals("XIANGMUJINGLI")) {
			queryMap.put("roleId", role.getEntityId());
			queryMap.put("resourceId", resId);
		}
		if (roleCode.equals("FUNENGQUN")) {
			queryMap.put("roleId", role.getEntityId());
			/* queryMap.put("resourceId", resId); */
		}
		if (roleCode.equals("FENGKONG")) {
			queryMap.put("roleId", role.getEntityId());
			queryMap.put("resourceId", resId);
		}
		if (roleCode.equals("CFO")) {
			queryMap.put("roleId", role.getEntityId());
		}
		if (roleCode.equals("CEO")) {
			queryMap.put("roleId", role.getEntityId());
		}
		if (roleCode.equals("ZONGCAI")) {
			queryMap.put("roleId", role.getEntityId());
		}
		queryMap.put("state", 0);
		result.addAll(this.selectByMap(queryMap));
		return result;
	}

	@Override
	//@Transactional
	public boolean insertTodo(Long projectId, Long roleId, Long resourceId, String itcode, Integer type) {
		ProjectTodo entity = new ProjectTodo();
		entity.setCreateTime(new Date());
		entity.setState(0);
		entity.setRoleId(roleId);
		entity.setProjectId(projectId);
		if (resourceId != null) {
			entity.setResourceId(resourceId);
			if (itcode != null) {
				entity.setItcode(itcode);
			}
		}
		entity.setType(type);
		boolean insert = this.insert(entity);
		return insert;
	}

	/**
	 * 
	 * @param todo
	 * @return
	 */
	public boolean insertTodo(ProjectTodo todo) {
		if (todo == null)
			return false;
		todo.setCreateTime(new Date());
		todo.setState(0);
		return this.insert(todo);
	}

	@Override
	//@Transactional
	public boolean deleteTodo(Long projectId, Long roleId, Long resourceId, String itcode, Integer type) {
		EntityWrapper<ProjectTodo> wrapper = new EntityWrapper<>();
		if (projectId != null) {
			wrapper.eq("projectId", projectId);
		}
		if (roleId != null) {
			wrapper.eq("roleId", roleId);
		}
		if (resourceId != null) {
			wrapper.eq("resourceId", resourceId);
		}
		if (itcode != null) {
			wrapper.eq("itcode", itcode);
		}
		if (type != null) {
			wrapper.eq("type", type);
		}
		List<ProjectTodo> selectList = this.selectList(wrapper);
		boolean deleteBatchIds = false;
		if (selectList != null && selectList.size() > 0) {
			List<Long> ids = new ArrayList<>();
			for (ProjectTodo p : selectList) {
				ids.add(p.getEntityId());
			}
			deleteBatchIds = this.deleteBatchIds(ids);
		}
		return deleteBatchIds;
	}

	@Override
	public List<ProjectTodo> getProjectTodos(List<RoleEntity> roles, Long resId) {
		return this.baseMapper.getProjectTodos(roles, resId);
	}

	/**
	 * 插入待办事项
	 */
	@Override
	public Map<String, Object> insertTodos(Long projectId, String taskCode) {
		return this.baseMapper.insertTodos(projectId, taskCode);
	}

	@Override
	public Boolean isExit(Long projectId, Long roleId, Long resourceId, String groupCode, Integer type) {
		Boolean result = false;
		EntityWrapper<ProjectTodo> wrapper = new EntityWrapper<>();
		wrapper.eq("projectId", projectId);
		wrapper.eq("roleId", roleId);
		if (resourceId != null && resourceId != 0) {
			wrapper.eq("resourceId", resourceId);
		}
		if (groupCode != null && !groupCode.equals("")) {
			wrapper.eq("groupCode", groupCode);
		}
		if (type != null && type != 0) {
			wrapper.eq("type", type);
		}
		List<ProjectTodo> selectList = this.baseMapper.selectList(wrapper);
		if(selectList!= null && selectList.size()>0) {
			result = true;
		}
		return result;
	}

}
