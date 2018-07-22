package com.river.ms.system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.river.core.entity.RoleFunctionEntity;
import com.river.core.service.ServiceBase;
import com.river.ms.system.mapper.IRoleFunctionMapper;
@Service
public class RoleFunctionService extends ServiceBase<IRoleFunctionMapper, RoleFunctionEntity> {
	@Autowired
	IRoleFunctionMapper mapper;
	@Override
	public IRoleFunctionMapper getDao() {
		// TODO Auto-generated method stub
		return mapper;
	}

}
