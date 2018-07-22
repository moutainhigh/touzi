package com.river.ms.system.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.river.core.entity.ElementEntity;
import com.river.core.service.ServiceTreeBase;
import com.river.ms.system.mapper.IElementMapper;

/**
 * 
 * @author zhouheng
 *
 */
@Service
//@Transactional(value="transactionManager")
public class ElementService extends ServiceTreeBase<IElementMapper,ElementEntity>{
	@Autowired
	IElementMapper mapper;

	@Override
	public IElementMapper getDao() {
		return mapper;
	}
	
	

}
