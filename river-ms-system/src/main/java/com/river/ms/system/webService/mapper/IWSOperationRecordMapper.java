package com.river.ms.system.webService.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.river.ms.system.webService.entity.WSOperationRecord;

@Mapper
public interface IWSOperationRecordMapper {
	
	int insert(List<WSOperationRecord> WSOperationRecords);
	
}