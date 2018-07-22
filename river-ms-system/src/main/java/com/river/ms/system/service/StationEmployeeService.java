package com.river.ms.system.service;

import org.springframework.stereotype.Service;

import com.river.core.service.ServiceBase;
import com.river.core.entity.StationEmployeeEntity;
import com.river.ms.system.mapper.IStationEmployeeMapper;
@Service
public abstract class StationEmployeeService extends ServiceBase<IStationEmployeeMapper, StationEmployeeEntity> {
	IStationEmployeeMapper mapper;
	@Override
	public IStationEmployeeMapper getDao() {
		// TODO Auto-generated method stub
		return mapper;
	}
	
	

}
