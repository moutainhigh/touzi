package com.river.core.entity;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import com.river.core.annotation.RiverColumn;
import com.river.core.annotation.RiverTable;
import com.river.core.entity.EntityBase;

/**
 * 人员和岗位关系表
 * 
 * @author my
 *
 */
@RiverTable(Name = "sys_ref_stationEmployee")
public class StationEmployeeEntity extends EntityBase implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@RiverColumn(Name = "organizationId")
	@NotNull
	public Long organizationId;
	@RiverColumn
	@NotNull
	public Long stationId;
	@RiverColumn
	@NotNull
	public Long employeeId;

	/**
	 * 是否是主要岗位 1 = 主要岗位 0 = 次要岗位
	 */
	@RiverColumn
	public Integer isChief;

	public OrganizationEntity organization;

	public OrganizationEntity hangyegongsi;

	public StationEntity station;

	public StationEntity getStation() {
		return station;
	}

	public void setStation(StationEntity station) {
		this.station = station;
	}

	public Long getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Long organizationId) {
		this.organizationId = organizationId;
	}

	public Long getStationId() {
		return stationId;
	}

	public void setStationId(Long stationId) {
		this.stationId = stationId;
	}

	public Long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
	}

	public Integer getIsChief() {
		return isChief;
	}

	public void setIsChief(Integer isChief) {
		this.isChief = isChief;
	}

	public OrganizationEntity getOrganization() {
		return organization;
	}

	public void setOrganization(OrganizationEntity organization) {
		this.organization = organization;
	}

	public OrganizationEntity getHangyegongsi() {
		return hangyegongsi;
	}

	public void setHangyegongsi(OrganizationEntity hangyegongsi) {
		this.hangyegongsi = hangyegongsi;
	}

}
