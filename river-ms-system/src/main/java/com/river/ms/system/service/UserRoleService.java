package com.river.ms.system.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.river.core.entity.UserRoleEntity;
import com.river.core.service.ServiceBase;
import com.river.ms.system.mapper.IUserRoleMapper;
@Service
public class UserRoleService extends ServiceBase<IUserRoleMapper, UserRoleEntity> {
	@Autowired
	IUserRoleMapper mapper;
	@Override
	public IUserRoleMapper getDao() {
		// TODO Auto-generated method stub
		return mapper;
	}
	
	

}
