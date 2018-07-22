package com.river.ms.system.webService.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.river.ms.system.webService.entity.User;

@Mapper
public interface IUserWBMapper {
	
	int insert(List<User> users);
	
}