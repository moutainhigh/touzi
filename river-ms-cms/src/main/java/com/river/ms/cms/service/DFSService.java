package com.river.ms.cms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.river.ms.cms.entity.DFSEntity;
import com.river.ms.cms.mapper.IDFSMapper;

@Service
public class DFSService {

	@Autowired
	IDFSMapper mapper;

	public void insertBitch(List<DFSEntity> parseArray) {
		this.mapper.insertBitch(parseArray);
	}

}
