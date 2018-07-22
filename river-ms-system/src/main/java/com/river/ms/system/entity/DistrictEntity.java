package com.river.ms.system.entity;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

import com.river.core.annotation.RiverColumn;
import com.river.core.annotation.RiverTable;
import com.river.core.entity.TreeEntityBase;
import com.river.core.validator.Regex;
/**
 * 
 * @author zhouheng
 *
 */
@RiverTable(Name="sys_district")
public class DistrictEntity extends TreeEntityBase{
	@RiverColumn
	@Length(min=0,max=50,message="entityAB长度不能超过50")
	@Pattern(regexp=Regex.CHINESE,message="简称只能是注解")
	public String entityAB;
	@RiverColumn
	@Length(min=0,max=50,message="entityEN长度不能超过50")
	public String entityEN;
	@RiverColumn
	@NotNull
	public int entityType;
	public String getEntityAB() {
		return entityAB;
	}
	public void setEntityAB(String entityAB) {
		this.entityAB = entityAB;
	}
	public String getEntityEN() {
		return entityEN;
	}
	public void setEntityEN(String entityEN) {
		this.entityEN = entityEN;
	}
	public int getEntityType() {
		return entityType;
	}
	public void setEntityType(int entityType) {
		this.entityType = entityType;
	}

	
	

}
