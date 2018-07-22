package com.river.core.entity;


import java.io.Serializable;

import javax.validation.constraints.NotNull;

import com.river.core.annotation.RiverColumn;
import com.river.core.annotation.RiverTable;
import com.river.core.entity.EntityBase;
/**
 * 
 * @author 公司和部门
 *
 */
@RiverTable(Name="sys_ref_organStation")
public class OrganizationStationEntity extends EntityBase implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@RiverColumn
	@NotNull
	public Long organizationId;
	@RiverColumn
	@NotNull
	public Long stationId;
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
}
