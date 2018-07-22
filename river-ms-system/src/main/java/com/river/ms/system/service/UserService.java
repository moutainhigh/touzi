package com.river.ms.system.service;


import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.river.core.entity.UserEntity;
import com.river.core.entity.UserRoleEntity;
import com.river.core.service.ServiceBase;
import com.river.ms.system.mapper.IUserMapper;

/**
 * 
 * @author zhouheng
 *
 */
@Service
public class UserService extends ServiceBase<IUserMapper,UserEntity>{
	@Autowired
	IUserMapper userMapper;

	@Override
	public IUserMapper getDao() {
		// TODO Auto-generated method stub
		return userMapper;
	}
	public boolean addRoles(List<UserRoleEntity> roles) {
		return userMapper.addRoles(roles);
	}
	public List<UserEntity> search(String keyWords, String entityCode,Date beginTime, Date endTime) {
		return userMapper.search(keyWords, entityCode,beginTime, endTime);
	}
	public List<Long> listUserRoles(long userId) {
		return userMapper.listUserRoles(userId);
	}
	public boolean removeRoleUser(List<Long> userRoleId,long roleId) {
		return userMapper.removeRoleUser(userRoleId, roleId);
	}
	public boolean disableRoleUser(List<Long> userRoleId, long roleId) {
		return userMapper.disableRoleUser(userRoleId, roleId);
	}
	public List<UserEntity> getUser(List<Long> userId) {
		return userMapper.getUser(userId);
	}

}
