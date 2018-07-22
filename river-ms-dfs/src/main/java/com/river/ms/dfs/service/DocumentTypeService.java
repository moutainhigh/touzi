package com.river.ms.dfs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.river.core.service.ServiceTreeBase;
import com.river.ms.dfs.entity.DocumentType;
import com.river.ms.dfs.mapper.IDocumentTypeMapper;

/**
 * 
 * @author my
 *
 */
@Service
public class DocumentTypeService extends ServiceTreeBase<IDocumentTypeMapper, DocumentType> {
	
	@Autowired
	IDocumentTypeMapper mapper;

	@Override
	public IDocumentTypeMapper getDao() {
		return mapper;
	}

}
