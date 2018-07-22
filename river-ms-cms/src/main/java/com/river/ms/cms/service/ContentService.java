package com.river.ms.cms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.river.core.service.ServiceBase;
import com.river.ms.cms.entity.ContentEntity;
import com.river.ms.cms.mapper.IContentMapper;
/**
 * 
 * @author zhouheng
 *
 */
@Service
public class ContentService extends ServiceBase<IContentMapper, ContentEntity> {
	@Autowired
	IContentMapper mapper;
	@Override
	public IContentMapper getDao() {
		// TODO Auto-generated method stub
		return mapper;
	}

}
