package com.river.ms.system.service;



import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.river.core.entity.EmployeeEntity;
import com.river.core.entity.StationEmployeeEntity;
import com.river.core.entity.StationEntity;
import com.river.core.service.ServiceBase;
import com.river.ms.system.mapper.IStationMapper;

/**
 * 
 * @author zhouheng
 *
 */
@Service
public class StationService extends ServiceBase<IStationMapper,StationEntity>{
	@Autowired
	IStationMapper mapper;

	@Override
	public IStationMapper getDao() {
		// TODO Auto-generated method stub
		return mapper;
	}
	
	public boolean addEmployees(List<StationEmployeeEntity> employees) {
		return mapper.addEmployees(employees);
	}
	public List<StationEntity> search(String keyWords, String entityCode,Date beginTime, Date endTime) {
		return mapper.search(keyWords,entityCode, beginTime, endTime);
	}
	
	public List<StationEmployeeEntity> listStationEmployees(long stationId) {
		return mapper.listStationEmployees(stationId);
	}
	public boolean removeStationEmployee(long stationId, List<Long> employeeId) {
		return mapper.removeStationEmployee(stationId, employeeId);
	}
	public boolean disableStationEmployee(long stationId, List<Long>  employeeId) {
		return mapper.disableStationEmployee(stationId, employeeId);
	}
	
	public List<EmployeeEntity> selectableEmployees(long stationId,String keyWords) {
		return mapper.selectableEmployees(stationId, keyWords);
	}
}
