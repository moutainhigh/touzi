package com.river.ms.system.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.river.core.service.ServiceBase;
import com.river.ms.system.entity.DictionaryEntity;
import com.river.ms.system.mapper.IDictionaryMapper;

/**
 * 
 * @author zhouheng
 *
 */
@Service
public class DictionaryService extends ServiceBase<IDictionaryMapper,DictionaryEntity>{
	@Autowired
	IDictionaryMapper mapper;

	@Override
	public IDictionaryMapper getDao() {
		// TODO Auto-generated method stub
		return mapper;
	}
	
	

}
