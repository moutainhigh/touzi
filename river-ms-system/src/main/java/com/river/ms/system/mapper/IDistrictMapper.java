package com.river.ms.system.mapper;

import com.river.core.dao.IDAOBase;
import com.river.ms.system.entity.DistrictEntity;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface IDistrictMapper extends IDAOBase<DistrictEntity> {
	DistrictEntity getParentE(@Param("parentId")long parentId);
	
}