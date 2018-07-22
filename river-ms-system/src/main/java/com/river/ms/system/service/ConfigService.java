package com.river.ms.system.service;


import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.river.core.service.ServiceBase;
import com.river.ms.system.entity.ConfigEntity;
import com.river.ms.system.mapper.IConfigMapper;

/**
 * 
 * @author zhouheng
 *
 */
@Service
public class ConfigService extends ServiceBase<IConfigMapper,ConfigEntity>{
	@Autowired
	IConfigMapper mapper;

	@Override
	public IConfigMapper getDao() {
		// TODO Auto-generated method stub
		return mapper;
	}
	
	public boolean updateConfig(List<ConfigEntity> configs) {
		return mapper.updateConfig(configs);
	}
	public Date getNow(){
		return mapper.getNow();
	}
}
