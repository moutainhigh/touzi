package com.river.ms.system.mapper;

import com.river.core.dao.IDAOBase;
import com.river.core.entity.ElementEntity;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface IElementMapper extends IDAOBase<ElementEntity> {
	ElementEntity getParentE(@Param("parentId")long parentId);
	
	List<ElementEntity> getElementByFunction(@Param("parentId")long parentId,@Param("functions")List<String> functions);
}