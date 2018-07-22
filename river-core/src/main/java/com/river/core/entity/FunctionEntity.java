package com.river.core.entity;
import java.io.Serializable;

import javax.validation.constraints.NotNull;

import com.river.core.annotation.RiverColumn;
import com.river.core.annotation.RiverTable;
import com.river.core.entity.TreeEntityBase;
/**
 * 
 * @author zhouheng
 * 功能表
 */
@RiverTable(Name="sys_function")
public class FunctionEntity extends TreeEntityBase implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 *  1：模块  2 ：功能
	 */
	@RiverColumn
	@NotNull
	public Integer functionType;

	public Integer getFunctionType() {
		return functionType;
	}

	public void setFunctionType(Integer functionType) {
		this.functionType = functionType;
	}
	
	
}
