package com.river.core.entity;

import java.io.Serializable;
import java.util.List;

import com.river.core.annotation.RiverTable;
import com.river.core.entity.TreeEntityBase;

/**
 * 
 * @author zhouheng 角色
 */
@RiverTable(Name = "sys_role")
public class RoleEntity extends TreeEntityBase implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String userName;
	public long userId;
	public long roleId;
	public String functionName;
	public long functionId;
	public int isGroupLeader;
	public int isExpert;
	public int appIndexType;

	public int getIsExpert() {
		return isExpert;
	}

	public void setIsExpert(int isExpert) {
		this.isExpert = isExpert;
	}

	public int getAppIndexType() {
		return appIndexType;
	}

	public void setAppIndexType(int appIndexType) {
		this.appIndexType = appIndexType;
	}

	/**
	 * @return the isGroupLeader
	 */
	public int getIsGroupLeader() {
		return isGroupLeader;
	}

	/**
	 * @param isGroupLeader
	 *            the isGroupLeader to set
	 */
	public void setIsGroupLeader(int isGroupLeader) {
		this.isGroupLeader = isGroupLeader;
	}

	/**
	 * @return the isComLeader
	 */
	public int getIsComLeader() {
		return isComLeader;
	}

	/**
	 * @param isComLeader
	 *            the isComLeader to set
	 */
	public void setIsComLeader(int isComLeader) {
		this.isComLeader = isComLeader;
	}

	/**
	 * @return the isThinktank
	 */
	public int getIsThinktank() {
		return isThinktank;
	}

	/**
	 * @param isThinktank
	 *            the isThinktank to set
	 */
	public void setIsThinktank(int isThinktank) {
		this.isThinktank = isThinktank;
	}

	public int isComLeader;
	public int isThinktank;

	/**
	 * 角色拥有的功能
	 */
	public List<FunctionEntity> functions;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public long getRoleId() {
		return roleId;
	}

	public void setRoleId(long roleId) {
		this.roleId = roleId;
	}

	public String getFunctionName() {
		return functionName;
	}

	public void setFunctionName(String functionName) {
		this.functionName = functionName;
	}

	public long getFunctionId() {
		return functionId;
	}

	public void setFunctionId(long functionId) {
		this.functionId = functionId;
	}

	public RoleEntity(String userName, long userId, long roleId, String functionName, long functionId,
			List<FunctionEntity> functions) {
		super();
		this.userName = userName;
		this.userId = userId;
		this.roleId = roleId;
		this.functionName = functionName;
		this.functionId = functionId;
		this.functions = functions;
	}

	public RoleEntity() {
		super();
	}

	public List<FunctionEntity> getFunctions() {
		return functions;
	}

	public void setFunctions(List<FunctionEntity> functions) {
		this.functions = functions;
	}

}
