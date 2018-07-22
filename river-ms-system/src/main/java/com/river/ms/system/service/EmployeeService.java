package com.river.ms.system.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.river.core.service.ServiceBase;
import com.river.core.entity.EmployeeEntity;
import com.river.ms.system.mapper.IEmployeeMapper;

/**
 * 
 * @author zhouheng
 *
 */
@Service
public class EmployeeService extends ServiceBase<IEmployeeMapper, EmployeeEntity> {
	@Autowired
	IEmployeeMapper mapper;

	@Override
	public IEmployeeMapper getDao() {
		return mapper;
	}

	public List<EmployeeEntity> search(String keyWords, String entityCode, String idCard, String mobile, String email,
			Date beginTime, Date endTime) {
		return mapper.search(keyWords, entityCode, idCard, mobile, email, beginTime, endTime);
	}

	public List<EmployeeEntity> getGuidByItCodes(List<String> itCodes) {
		return mapper.getGuidByItCodes(itCodes);
	}

	public List<EmployeeEntity> getEmp(Long organizationId, Long stationId, boolean isExit, String keyword) {
		return mapper.getEmp(organizationId, stationId, isExit,keyword);
	}
	
	public List<EmployeeEntity> getEmployeeEntity(String keyword) {
		return mapper.getEmployeeEntity(keyword);
	}

}
