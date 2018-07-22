package com.river.ms.system.mapper;

import com.river.core.dao.IDAOBase;
import com.river.ms.system.entity.ConfigEntity;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
@Mapper
public interface IConfigMapper extends IDAOBase<ConfigEntity> {
	boolean updateConfig(List<ConfigEntity> configs);
	Date getNow();
}