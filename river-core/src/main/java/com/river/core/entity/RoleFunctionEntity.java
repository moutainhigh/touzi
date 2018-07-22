package com.river.core.entity;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import com.river.core.annotation.RiverColumn;
import com.river.core.annotation.RiverTable;
import com.river.core.entity.EntityBase;
@RiverTable(Name="sys_ref_roleFunction")
public class RoleFunctionEntity extends EntityBase implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@RiverColumn
	@NotNull
	public Long functionId;
	
	@RiverColumn
	@NotNull
	public Long roleId;
	public Long getFunctionId() {
		return functionId;
	}
	public void setFunctionId(Long functionId) {
		this.functionId = functionId;
	}
	
	public Long getRoleId() {
		return roleId;
	}
	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
	
}
