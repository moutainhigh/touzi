package com.river.ms.system.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.river.core.entity.FunctionEntity;
import com.river.core.entity.RoleEntity;
import com.river.core.entity.UserEntity;

@Mapper
public interface IUserRoleFunction {
	
	/**
	 * 根据用户code获取用户信息
	 * @param code
	 * @return
	 */
	UserEntity getUserByCode(@Param("code") String code);
	
	/**
	 * 获取用户的角色
	 * @param id
	 * @return
	 */
	List<RoleEntity> getRolesByUserId(@Param("id") String id);
	
	/**
	 * 获取角色的功能
	 * @param id
	 * @return
	 */
	List<FunctionEntity> getFunctionsByRoleId(@Param("id") String id);
}
