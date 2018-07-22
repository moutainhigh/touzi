package com.river.core.entity;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.NotNull;

import com.river.core.annotation.RiverColumn;
import com.river.core.annotation.RiverTable;
import com.river.core.entity.TreeEntityBase;

/**
 * 组织机构
 * 
 * @author zhouheng
 *
 */
@RiverTable(Name = "sys_organization")
public class OrganizationEntity extends TreeEntityBase implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 组织类型 0-集团1-产业板块2-产业公司4-部门
	 */
	@RiverColumn
	@NotNull
	public int organType;

	public List<StationEntity> stations;

	public int getOrganType() {
		return organType;
	}

	public void setOrganType(int organType) {
		this.organType = organType;
	}

	public List<StationEntity> getStations() {
		return stations;
	}

	public void setStations(List<StationEntity> stations) {
		this.stations = stations;
	}

}
