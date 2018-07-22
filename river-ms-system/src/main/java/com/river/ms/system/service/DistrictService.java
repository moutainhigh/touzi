package com.river.ms.system.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.river.core.service.ServiceTreeBase;
import com.river.ms.system.entity.DistrictEntity;
import com.river.ms.system.mapper.IDistrictMapper;

/**
 * 
 * @author zhouheng
 *
 */
@Service
public class DistrictService extends ServiceTreeBase<IDistrictMapper,DistrictEntity>{
	@Autowired
	IDistrictMapper mapper;

	@Override
	public IDistrictMapper getDao() {
		// TODO Auto-generated method stub
		return mapper;
	}
	
	

}
