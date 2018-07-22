package com.river.ms.system.mapper;

import com.river.core.dao.IDAOBase;
import com.river.core.entity.FunctionEntity;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface IFunctionMapper extends IDAOBase<FunctionEntity> {
	FunctionEntity getParentE(@Param("parentId")long parentId);
}