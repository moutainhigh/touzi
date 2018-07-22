package com.river.ms.dfs.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.river.core.dao.IDAOBase;
import com.river.ms.dfs.entity.DocumentType;

/**
 * 
 * @author my
 *
 */
@Mapper
public interface IDocumentTypeMapper extends IDAOBase<DocumentType> {

}
