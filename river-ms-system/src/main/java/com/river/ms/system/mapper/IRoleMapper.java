package com.river.ms.system.mapper;

import com.river.core.dao.IDAOBase;
import com.river.core.entity.RoleEntity;
import com.river.core.entity.RoleFunctionEntity;
import com.river.core.entity.UserEntity;
import com.river.core.entity.UserRoleEntity;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface IRoleMapper extends IDAOBase<RoleEntity> {
	RoleEntity getParentE(@Param("parentId") long parentId);

	boolean addFunctions(List<RoleFunctionEntity> functions);

	long selectLastId();

	boolean deleteRoles(long roleId);

	List<Long> listRoleFunction(long roleId);

	List<UserRoleEntity> listUsers(long roleId);

	boolean removeUserRole(@Param("roleId") long roleId, @Param("roleUserId") List<Long> roleUserId);

	boolean disableUserRole(@Param("roleId") long roleId, @Param("roleUserId") List<Long> roleUserId);

	boolean addUsers(List<UserRoleEntity> users);

	List<UserEntity> selectableUser(@Param("roleId") long roleId, @Param("keyWords") String keyWords);

	List<UserRoleEntity> getRoleUser(@Param("roleId") Long roleId, @Param("keyword") String keyword);
}