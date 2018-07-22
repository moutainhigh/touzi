package com.river.ms.system.mapper;

import com.river.core.dao.IDAOBase;
import com.river.core.entity.EmployeeEntity;
import com.river.core.entity.StationEmployeeEntity;
import com.river.core.entity.StationEntity;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface IStationMapper extends IDAOBase<StationEntity> {
	boolean addEmployees(List<StationEmployeeEntity> employees);
	List<StationEntity> search(@Param("keyWords") String keyWords, @Param("entityCode") String entityCode,@Param("beginTime") Date beginTime,@Param("endTime") Date endTime);
	List<StationEmployeeEntity> listStationEmployees(long stationId);
	boolean removeStationEmployee(@Param("stationId") long stationId, @Param("staionEmployeeId") List<Long> staionEmployeeId);
	boolean disableStationEmployee(@Param("stationId") long stationId, @Param("staionEmployeeId") List<Long> staionEmployeeId);
	List<EmployeeEntity> selectableEmployees(@Param("stationId") long stationId,@Param("keyWords") String keyWords);
	List<StationEntity> selectStationEntitysByOrgid(@Param("organizationId") long organizationId);
}