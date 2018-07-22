package com.river.ms.system.service;


import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.river.core.service.ServiceBase;
import com.river.ms.system.entity.ThirdGroupEntity;
import com.river.ms.system.mapper.IThirdGroupMapper;

/**
 * 
 * @author zhouheng
 *
 */
@Service
public class ThirdGroupService extends ServiceBase<IThirdGroupMapper,ThirdGroupEntity>{
	@Autowired
	IThirdGroupMapper mapper;

	@Override
	public IThirdGroupMapper getDao() {
		// TODO Auto-generated method stub
		return mapper;
	}
	
	public List<ThirdGroupEntity> search(String keyWords,String entityCode,String contact,String mobile,String email, Date beginTime, Date endTime) {
		return mapper.search(keyWords,entityCode,contact,mobile,email, beginTime, endTime);
	}
	
	public List<ThirdGroupEntity> selectByGroupType(Integer type) {
		return mapper.selectByGroupType(type);
	}

}
