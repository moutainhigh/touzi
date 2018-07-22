package com.river.ms.system.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.river.core.entity.MenuEntity;
import com.river.core.service.ServiceTreeBase;
import com.river.ms.system.mapper.IMenuMapper;

/**
 * 
 * @author zhouheng
 *
 */
@Service
public class MenuService extends ServiceTreeBase<IMenuMapper,MenuEntity>{
	@Autowired
	IMenuMapper mapper;

	@Override
	public IMenuMapper getDao() {
		// TODO Auto-generated method stub
		return mapper;
	}
	
	public List<MenuEntity> getMenuByIds(List<Long> ids){
		return mapper.getMenuByIds(ids);
	}

}
