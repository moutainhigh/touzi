package com.river.ms.system.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.river.core.service.ServiceTreeBase;
import com.river.ms.system.entity.IndustryEntity;
import com.river.ms.system.mapper.IIndustryMapper;

/**
 * 
 * @author zhouheng
 *
 */
@Service
public class IndustryService extends ServiceTreeBase<IIndustryMapper,IndustryEntity>{
	@Autowired
	IIndustryMapper mapper;

	@Override
	public IIndustryMapper getDao() {
		// TODO Auto-generated method stub
		return mapper;
	}
	
	

}
