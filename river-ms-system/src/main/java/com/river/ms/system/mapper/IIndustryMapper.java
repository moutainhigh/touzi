package com.river.ms.system.mapper;

import com.river.core.dao.IDAOBase;
import com.river.ms.system.entity.IndustryEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface IIndustryMapper extends IDAOBase<IndustryEntity> {
	IndustryEntity getParentE(@Param("parentId")long parentId);
}