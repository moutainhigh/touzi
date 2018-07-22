package com.river.ms.system.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.river.core.service.ServiceTreeBase;
import com.river.ms.system.entity.DictionaryItemEntity;
import com.river.ms.system.mapper.IDictionaryItemMapper;

/**
 * 
 * @author zhouheng
 *
 */
@Service
public class DictionaryItemService extends ServiceTreeBase<IDictionaryItemMapper,DictionaryItemEntity>{
	@Autowired
	IDictionaryItemMapper mapper;

	@Override
	public IDictionaryItemMapper getDao() {
		// TODO Auto-generated method stub
		return mapper;
	}
}
