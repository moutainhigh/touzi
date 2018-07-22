package com.river.ms.system.mapper;

import com.river.core.dao.IDAOBase;
import com.river.ms.system.entity.LogEntity;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface ILogMapper extends IDAOBase<LogEntity> {
	List<LogEntity> queryAll();
	List<LogEntity> queryByLimit(@Param("str") String str, @Param("beginTime") Date beginTime, @Param("endTime")Date endTime);
}