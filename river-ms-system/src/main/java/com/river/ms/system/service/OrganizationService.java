package com.river.ms.system.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.river.core.service.ServiceTreeBase;
import com.river.core.entity.OrganizationEntity;
import com.river.core.entity.OrganizationStationEntity;
import com.river.core.entity.StationEmployeeEntity;
import com.river.ms.system.mapper.IOrganizationMapper;

/**
 * 
 * @author zhouheng
 *
 */
@Service
public class OrganizationService extends ServiceTreeBase<IOrganizationMapper, OrganizationEntity> {
	@Autowired
	IOrganizationMapper mapper;

	@Override
	public IOrganizationMapper getDao() {
		// TODO Auto-generated method stub
		return mapper;
	}

	public boolean addStations(List<OrganizationStationEntity> stations) {
		return mapper.addStation(stations);
	}

	public boolean addEmployees(List<StationEmployeeEntity> employees) {
		return mapper.addEmployees(employees);
	}

	public void insertOrgAndStation(List<Long> stationIds, List<Long> organizationIds) {
		for (Long orgId : organizationIds) {
			this.mapper.deleteOrgAndStation(orgId);
		}
		this.mapper.insertOrgAndStation(stationIds, organizationIds);
	}

	public OrganizationEntity getOrganizationEntity(Long organizationId) {
		return this.mapper.getOrganizationEntity(organizationId);
	}

	public void insertOrgAndStationAndEmp(Long stationId, Long organizationId, List<Long> empIds) {
		this.mapper.insertOrgAndStationAndEmp(stationId, organizationId, empIds);
	}

	public void deleteOrgAndStationAndEmp(Long stationId, Long organizationId, List<Long> empIds) {
		this.mapper.deleteOrgAndStationAndEmp(stationId, organizationId, empIds);
	}

	public void setOrgAndStationAndEmpToChief(Long stationId, Long organizationId, Long empId) {
		this.mapper.updateOrgAndStationAndEmpToNoChief(empId);
		this.mapper.updateOrgAndStationAndEmpToChief(stationId, organizationId, empId);
	}

	public List<String> getOrgCodes(List<String> orgCodes) {
		List<String> result = new ArrayList<>();
		if (orgCodes != null && orgCodes.size() > 0) {
			List<OrganizationEntity> queryAll = this.mapper.queryAll();
			this.getChildOrgCode(result, orgCodes, queryAll);
		}
		return result;
	}

	private void getChildOrgCode(List<String> result, List<String> orgCodes, List<OrganizationEntity> queryAll) {
		List<OrganizationEntity> orgs = new ArrayList<>();
		for (OrganizationEntity organizationEntity : queryAll) {
			if (orgCodes.contains(organizationEntity.getEntityCode())) {
				orgs.add(organizationEntity);
				result.add(organizationEntity.getEntityCode());
			}
		}
		if (orgs != null && orgs.size() > 0) {
			for (OrganizationEntity organizationEntity : orgs) {
				this.getchildCode(organizationEntity,queryAll,result);
			}
		}
	}

	private void getchildCode(OrganizationEntity organizationEntity, List<OrganizationEntity> queryAll,
			List<String> result) {
		for(OrganizationEntity org : queryAll) {
			if(org.getParentId() == organizationEntity.getEntityId()) {
				this.getchildCode(org, queryAll, result);
				result.add(org.getEntityCode());
			}
		}
	}
}
