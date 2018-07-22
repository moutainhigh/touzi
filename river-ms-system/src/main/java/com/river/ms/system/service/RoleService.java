package com.river.ms.system.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.river.core.entity.RoleEntity;
import com.river.core.entity.RoleFunctionEntity;
import com.river.core.entity.UserEntity;
import com.river.core.entity.UserRoleEntity;
import com.river.core.service.ServiceTreeBase;
import com.river.ms.system.mapper.IRoleMapper;

/**
 * 
 * @author zhouheng
 *
 */
@Service
public class RoleService extends ServiceTreeBase<IRoleMapper,RoleEntity>{
	@Autowired
	IRoleMapper mapper;

	@Override
	public IRoleMapper getDao() {
		// TODO Auto-generated method stub
		return mapper;
	}
	
	public boolean addFunctions(List<RoleFunctionEntity> functions) {
		return mapper.addFunctions(functions);
	}
	
	public boolean deleteRole(long roleId) {
		return mapper.deleteRoles(roleId);
	}
	
	public List<Long> listRoleFunction(long roleId) {
		return mapper.listRoleFunction(roleId);
	}
	public List<UserRoleEntity> listUsers(long roleId) {
		return mapper.listUsers(roleId);
	}
	
	public boolean removeUserRole(long roleId,List<Long> roleUserId){
		return mapper.removeUserRole(roleId,roleUserId);
	}
	public boolean disableUserRole(long roleId,List<Long> roleUserId) {
		return mapper.disableUserRole(roleId,roleUserId);
	}
	public boolean addUsers(List<UserRoleEntity> users) {
		return mapper.addUsers(users);
	}
	
	public List<UserEntity> selectableUser(long roleId,String keyWords) {
		return mapper.selectableUser(roleId,keyWords);
	}

	public List<UserRoleEntity> getRoleUser(Long roleId, String keyword) {
		// TODO Auto-generated method stub
		return mapper.getRoleUser(roleId,keyword);
	}

}
