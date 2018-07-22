package com.river.ms.dfs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.river.core.service.ServiceBase;
import com.river.ms.dfs.entity.Document;
import com.river.ms.dfs.mapper.IDocumentMapper;

/**
 * 
 * @author my
 *
 */
@Service
public class DocumentService extends ServiceBase<IDocumentMapper, Document> {
	
	@Autowired
	IDocumentMapper mapper;

	@Override
	public IDocumentMapper getDao() {
		return mapper;
	}

}
