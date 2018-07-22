package com.river.ms.system.mapper;

import com.river.core.dao.IDAOBase;
import com.river.ms.system.entity.DictionaryItemEntity;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface IDictionaryItemMapper extends IDAOBase<DictionaryItemEntity> {
	DictionaryItemEntity getParentE(@Param("parentId")long parentId);

}