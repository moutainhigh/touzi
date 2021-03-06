package com.river.core.entity;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import com.river.core.annotation.RiverColumn;
import com.river.core.annotation.RiverTable;
import com.river.core.entity.EntityBase;
@RiverTable(Name="sys_ref_userRole")
public class UserRoleEntity extends EntityBase implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@RiverColumn
	@NotNull
	public Long userId;
	@RiverColumn
	@NotNull
	public Long roleId;
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Long getRoleId() {
		return roleId;
	}
	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
	
	
}
