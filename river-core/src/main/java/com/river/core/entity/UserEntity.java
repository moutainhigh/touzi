package com.river.core.entity;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.river.core.annotation.RiverColumn;
import com.river.core.annotation.RiverTable;
import com.river.core.entity.EntityBase;

/**
 * 
 * @author zhouheng
 *
 */
@RiverTable(Name = "sys_user")
public class UserEntity extends EntityBase implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@RiverColumn
	@Length(min = 0, max = 30, message = "userPass长度不能超过30")
	public String userPass;
	@RiverColumn
	@Length(min = 0, max = 30, message = "passSalt长度不能超过10")
	public String passSalt;
	@RiverColumn
	@NotNull
	public int isExternal;

	@RiverColumn
	public Long employeeId;

	/**
	 * 对应的人员
	 */
	public EmployeeEntity employee;

	/**
	 * 用户所拥有的角色
	 */
	public List<RoleEntity> roles;

	private List<MenuEntity> menus;

	private List<ElementEntity> Elements;

	private Long resId;

	public String getUserPass() {
		return userPass;
	}

	public void setUserPass(String userPass) {
		this.userPass = userPass;
	}

	public String getPassSalt() {
		return passSalt;
	}

	public void setPassSalt(String passSalt) {
		this.passSalt = passSalt;
	}

	public int getIsExternal() {
		return isExternal;
	}

	public void setIsExternal(int isExternal) {
		this.isExternal = isExternal;
	}

	public List<RoleEntity> getRoles() {
		return roles;
	}

	public void setRoles(List<RoleEntity> roles) {
		this.roles = roles;
	}

	public List<MenuEntity> getMenus() {
		return menus;
	}

	public void setMenus(List<MenuEntity> menus) {
		this.menus = menus;
	}

	public List<ElementEntity> getElements() {
		return Elements;
	}

	public void setElements(List<ElementEntity> elements) {
		Elements = elements;
	}

	public Long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
	}

	public EmployeeEntity getEmployee() {
		return employee;
	}

	public void setEmployee(EmployeeEntity employee) {
		this.employee = employee;
	}

	public Long getResId() {
		return resId;
	}

	public void setResId(Long resId) {
		this.resId = resId;
	}

}
