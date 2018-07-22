package com.river.ms.bbs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.river.core.service.ServiceBase;
import com.river.ms.bbs.entity.ThumbupEntity;
import com.river.ms.bbs.mapper.IThumbupMapper;
/**
 * 
 * @author zhouheng
 *
 */
@Service
public class ThumbupService extends ServiceBase<IThumbupMapper, ThumbupEntity> {
	@Autowired
	IThumbupMapper mapper;
	@Override
	public IThumbupMapper getDao() {
		// TODO Auto-generated method stub
		return mapper;
	}

}
