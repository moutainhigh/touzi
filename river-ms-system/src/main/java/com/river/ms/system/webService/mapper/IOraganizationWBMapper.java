package com.river.ms.system.webService.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.river.ms.system.webService.entity.Organization;

@Mapper
public interface IOraganizationWBMapper {
	
	int insert(List<Organization> organizations);
	
}