package com.river.ms.system.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.river.core.entity.FunctionEntity;
import com.river.core.service.ServiceTreeBase;
import com.river.ms.system.mapper.IFunctionMapper;

/**
 * 
 * @author zhouheng
 *
 */
@Service
public class FunctionService extends ServiceTreeBase<IFunctionMapper,FunctionEntity>{
	@Autowired
	IFunctionMapper mapper;

	@Override
	public IFunctionMapper getDao() {
		// TODO Auto-generated method stub
		return mapper;
	}
	
	

}
