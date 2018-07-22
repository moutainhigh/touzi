package com.river.ms.system.service;


import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.river.core.service.ServiceBase;
import com.river.ms.system.entity.LogEntity;
import com.river.ms.system.mapper.ILogMapper;

/**
 * 
 * @author zhouheng
 *
 */
@Service
public class LogService extends ServiceBase<ILogMapper,LogEntity>{
	@Autowired
	ILogMapper mapper;

	@Override
	public ILogMapper getDao() {
		// TODO Auto-generated method stub
		return mapper;
	}
	
	public List<LogEntity> querryAll() {
		return mapper.queryAll();
	}
	
	public List<LogEntity> queryByLimit(String str, Date beginTime, Date endTime) {
		return mapper.queryByLimit(str, beginTime, endTime);
	}

}
