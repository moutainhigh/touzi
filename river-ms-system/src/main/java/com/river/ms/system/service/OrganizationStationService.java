package com.river.ms.system.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.river.core.service.ServiceBase;
import com.river.core.entity.OrganizationStationEntity;
import com.river.ms.system.mapper.IOrganizationStationMapper;
@Service
public class OrganizationStationService extends ServiceBase<IOrganizationStationMapper, OrganizationStationEntity> {
	@Autowired
	IOrganizationStationMapper mapper;
	@Override
	public IOrganizationStationMapper getDao() {
		// TODO Auto-generated method stub
		return mapper;
	}
	
	
		
}
