package com.river.core.entity;
import java.io.Serializable;

import javax.validation.constraints.NotNull;

import com.river.core.annotation.RiverColumn;
import com.river.core.annotation.RiverTable;
import com.river.core.entity.EntityBase;
/**
 * 
 * @author 岗位
 *
 */
@RiverTable(Name="sys_station")
public class StationEntity extends EntityBase implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@RiverColumn
	@NotNull
	public Integer stationType;

	public Integer getStationType() {
		return stationType;
	}

	public void setStationType(Integer stationType) {
		this.stationType = stationType;
	}
	
	public long employeeId;
	public String employeeName;

	public long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(long employeeId) {
		this.employeeId = employeeId;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	public long stationId;
	
}
