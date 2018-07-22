package com.river.ms.business.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.river.ms.business.entity.ProjectTodo;
import com.river.core.entity.RoleEntity;

/**
 * <p>
 * 待办事项：每次处理完更新此表 Mapper 接口
 * </p>
 *
 * @author zyb
 * @since 2017-12-13
 */
@Mapper
public interface ProjectTodoDao extends BaseMapper<ProjectTodo> {
	List<ProjectTodo> getProjectTodos(@Param("roles") List<RoleEntity> ids,@Param("resId") Long resId);
	Map<String,Object> insertTodos(@Param("projectId") Long projectId,@Param("taskCode") String taskCode);
}
