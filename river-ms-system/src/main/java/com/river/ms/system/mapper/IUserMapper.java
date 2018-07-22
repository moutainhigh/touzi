package com.river.ms.system.mapper;

import com.river.core.dao.IDAOBase;
import com.river.core.entity.RoleEntity;
import com.river.core.entity.UserEntity;
import com.river.core.entity.UserRoleEntity;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface IUserMapper extends IDAOBase<UserEntity> {
	boolean addRoles(List<UserRoleEntity> roles);
	List<UserEntity> search(@Param("keyWords") String keyWords,@Param("entityCode") String entityCode, @Param("beginTime") Date beginTime,@Param("endTime") Date endTime);
	List<Long> listUserRoles(long userId);
	boolean removeRoleUser(List<Long> userRoleId,@Param("roleId") long roleId);
	boolean disableRoleUser(List<Long> userRoleId,@Param("roleId") long roleId);
	List<RoleEntity> selectableRole(@Param("keyWords") String keyWords,@Param("userId") long userId);
	List<UserEntity> getUser(@Param("userIds") List<Long> userId);
}
