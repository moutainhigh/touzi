package com.river.ms.system.mapper;

import com.river.core.dao.IDAOBase;
import com.river.core.entity.OrganizationEntity;
import com.river.core.entity.OrganizationStationEntity;
import com.river.core.entity.StationEmployeeEntity;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface IOrganizationMapper extends IDAOBase<OrganizationEntity> {
	OrganizationEntity getParentE(@Param("parentId") long parentId);

	boolean addStation(List<OrganizationStationEntity> organizationStations);

	long selectLastId();

	boolean addEmployees(List<StationEmployeeEntity> employees);

	void insertOrgAndStation(@Param("stationIds") List<Long> stationIds,
			@Param("organizationIds") List<Long> organizationIds);

	void deleteOrgAndStation(@Param("organizationId") Long organizationId);

	OrganizationEntity getOrganizationEntity(@Param("organizationId") Long organizationId);

	void insertOrgAndStationAndEmp(@Param("stationId") Long stationId, @Param("organizationId") Long organizationId,
			@Param("empIds") List<Long> empIds);

	void deleteOrgAndStationAndEmp(@Param("stationId") Long stationId, @Param("organizationId") Long organizationId,
			@Param("empIds") List<Long> empIds);

	void updateOrgAndStationAndEmpToNoChief(@Param("empId") Long empId);

	void updateOrgAndStationAndEmpToChief(@Param("stationId") Long stationId,
			@Param("organizationId") Long organizationId, @Param("empId") Long empId);
}